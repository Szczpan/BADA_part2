package bdbt_bada_project.SpringApplication;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Types.NULL;
import static java.util.Objects.isNull;

@Configuration
public class AppController implements WebMvcConfigurer
{
    @Autowired
    private KlientDAO klientDAO;
    @Autowired
    private AdresDAO adresDAO;
    @Autowired
    private Kod_pocztowyDAO kodPocztowyDAO;
    @Autowired
    private PunktPoboruEnergiiDAO punktPoboruEnergiiDAO;
    @Autowired
    private FakturaDAO fakturaDAO;

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/main").setViewName("main");
        registry.addViewController("/login").setViewName("login");

        registry.addViewController("/main_admin").setViewName("admin/main_admin");
        registry.addViewController("/all_clients").setViewName("admin/all_clients");
        registry.addViewController("/all_supply_points").setViewName("admin/all_supply_points");
        registry.addViewController("/all_invoices").setViewName("admin/all_invoices");
        registry.addViewController("/all_clients/new").setViewName("admin/new_client");
        registry.addViewController("/all_clients:error").setViewName("admin/all_clients_error");
        registry.addViewController("/admin_supply_points").setViewName("admin/all_supply_points_for_client");




        registry.addViewController("/main_user").setViewName("user/main_user");
        registry.addViewController("/user_info").setViewName("user/user_info");
        registry.addViewController("/supply_points").setViewName("user/supply_points");
        registry.addViewController("/invoices").setViewName("user/invoices");
        registry.addViewController("/supply_points/new").setViewName("user/supply_points_new");

    }

    @Controller
    public class DashboardController
    {
        @RequestMapping("/main")
        public String defaultAfterLogin(HttpServletRequest request)
        {
            if
            (request.isUserInRole("ADMIN"))
            {
                return "redirect:/main_admin";
            }
            else if
            (request.isUserInRole("USER"))
            {
                return "redirect:/main_user";
            }
            else
            {
                return "redirect:/index";
            }
        }

        @RequestMapping("/index")
        public String redirectIndexAfterLogin(HttpServletRequest request)
        {
            if (request.isUserInRole("ADMIN"))
            {
                return  "redirect:/main_admin";
            } else if (request.isUserInRole("USER"))
            {
                return "redirect:/main_user";
            }
            else
            {
                return null;
            }
        }

        @RequestMapping("/")
        public String redirectToIndex(HttpServletRequest request)
        {
                return "redirect:/index";
        }


        @RequestMapping(value={"/main_admin"})
        public String showAdminPage(Model model) {
            return "admin/main_admin";
        }
        @RequestMapping(value={"/main_user"})
        public String showUserPage(Model model) {
            return "user/main_user";
        }

        @RequestMapping("/user_info")
        public ModelAndView showUserInfo(HttpServletRequest request)
        {
            String email = request.getRemoteUser();
            ModelAndView mav = new ModelAndView("user/user_info");
            Klient klient = klientDAO.get(email);
            Adres adres = adresDAO.get(klient.getNr_adresu());
            Kod_pocztowy kod_pocztowy = kodPocztowyDAO.get(adres.getKod_pocztowy());
            mav.addObject("klient", klient);
            mav.addObject("adres", adres);
            mav.addObject("kod_pocztowy", kod_pocztowy);
            return mav;
        }
        @RequestMapping("/supply_points")
        public ModelAndView showSupplyPoints(HttpServletRequest request)
        {
            String email = request.getRemoteUser();
            int client_nr = klientDAO.get(email).getNr_klienta();
            List<PunktPoboruEnergii> punktyPoboruEnergii = punktPoboruEnergiiDAO.listByNrKlienta(client_nr);

            List<Adres> adresList = new ArrayList<>();
            List<Kod_pocztowy> kod_pocztowyList = new ArrayList<>();
            for(PunktPoboruEnergii punktPoboruEnergii : punktyPoboruEnergii)
            {
                adresList.add(adresDAO.get(punktPoboruEnergii.getNr_adresu()));
                kod_pocztowyList.add(kodPocztowyDAO.get(adresDAO.get(punktPoboruEnergii.getNr_adresu()).getKod_pocztowy()));
            }

            List<SupplyPointAndAddress> supplyPointAndAddressList = new ArrayList<>();


            for(int i = 0; i < adresList.size(); i++)
            {
                supplyPointAndAddressList.add(new SupplyPointAndAddress(punktyPoboruEnergii.get(i), adresList.get(i), kod_pocztowyList.get(i)));
            }

            ModelAndView mav = new ModelAndView("user/supply_points");
            mav.addObject("SupplyPointsWithAddresses", supplyPointAndAddressList);
            return mav;
        }


        @RequestMapping("/supply_points/new")
        public ModelAndView showNewSupplyPoint()
        {
            ModelAndView mav = new ModelAndView("user/supply_points_new");
            SupplyPointAndAddress supplyPoint = new SupplyPointAndAddress();
            mav.addObject("supplyPoint", supplyPoint);

            return mav;
        }


        @RequestMapping(value = "/supply_points/new/insert", method = RequestMethod.POST)
        public String insertNewSupplyPoint(HttpServletRequest request, @ModelAttribute("supplyPoint") SupplyPointAndAddress supplyPoint)
        {
            String email = request.getRemoteUser();
            int client_nr = klientDAO.get(email).getNr_klienta();
            supplyPoint.punktPoboruEnergii.setNr_klienta(client_nr);

            try
            {
                kodPocztowyDAO.get(supplyPoint.kod_pocztowy.getKod_pocztowy());
            }
            catch (EmptyResultDataAccessException exception)
            {
                kodPocztowyDAO.save(supplyPoint.kod_pocztowy);
            }

            supplyPoint.adres.setKod_pocztowy(supplyPoint.kod_pocztowy.getKod_pocztowy()); // set postal code of address
            int nr_adresu = adresDAO.save(supplyPoint.adres); // add address to DB

            supplyPoint.punktPoboruEnergii.setNr_adresu(nr_adresu);

            punktPoboruEnergiiDAO.save(supplyPoint.punktPoboruEnergii);
            return "redirect:/supply_points";
        }


        @RequestMapping("/invoices")
        public ModelAndView showInvoicesForUser(HttpServletRequest request)
        {
            String email = request.getRemoteUser();
            int client_nr = klientDAO.get(email).getNr_klienta();
            List<Faktura> fakturaList = fakturaDAO.listByClient(client_nr);
            ModelAndView mav = new ModelAndView("user/invoices");
            mav.addObject("fakturaList", fakturaList);
            return mav;
        }

        @RequestMapping("/all_clients")
        public ModelAndView showAllClients()
        {
            List<Klient> klientList = klientDAO.list();
            ModelAndView mav = new ModelAndView("admin/all_clients");
            mav.addObject("klientList", klientList);
            return mav;
        }


        @RequestMapping("/all_supply_points")
        public ModelAndView showAllSupplyPoints()
        {
            List<PunktPoboruEnergii> punktyPoboruEnergii = punktPoboruEnergiiDAO.list();

            List<Adres> adresList = new ArrayList<>();
            List<Kod_pocztowy> kod_pocztowyList = new ArrayList<>();
            for(PunktPoboruEnergii punktPoboruEnergii : punktyPoboruEnergii)
            {
                adresList.add(adresDAO.get(punktPoboruEnergii.getNr_adresu()));
                kod_pocztowyList.add(kodPocztowyDAO.get(adresDAO.get(punktPoboruEnergii.getNr_adresu()).getKod_pocztowy()));
            }

            List<SupplyPointAndAddress> supplyPointAndAddressList = new ArrayList<>();


            for(int i = 0; i < adresList.size(); i++)
            {
                supplyPointAndAddressList.add(new SupplyPointAndAddress(punktyPoboruEnergii.get(i), adresList.get(i), kod_pocztowyList.get(i)));
            }

            ModelAndView mav = new ModelAndView("admin/all_supply_points");
            mav.addObject("SupplyPointsWithAddresses", supplyPointAndAddressList);
            return mav;
        }


        @RequestMapping("/all_invoices")
        public ModelAndView showAllInvoices()
        {
            List<Faktura> fakturaList = fakturaDAO.list();
            ModelAndView mav = new ModelAndView("admin/all_invoices");
            mav.addObject("fakturaList", fakturaList);
            return mav;
        }


        @RequestMapping("/admin_supply_points/{client_nr}")
        public ModelAndView showAllSupplyPointsForClient(@PathVariable(name = "client_nr") int client_nr)
        {
            List<PunktPoboruEnergii> punktyPoboruEnergii = punktPoboruEnergiiDAO.listByNrKlienta(client_nr);

            List<Adres> adresList = new ArrayList<>();
            List<Kod_pocztowy> kod_pocztowyList = new ArrayList<>();
            for(PunktPoboruEnergii punktPoboruEnergii : punktyPoboruEnergii)
            {
                adresList.add(adresDAO.get(punktPoboruEnergii.getNr_adresu()));
                kod_pocztowyList.add(kodPocztowyDAO.get(adresDAO.get(punktPoboruEnergii.getNr_adresu()).getKod_pocztowy()));
            }

            List<SupplyPointAndAddress> supplyPointAndAddressList = new ArrayList<>();


            for(int i = 0; i < adresList.size(); i++)
            {
                supplyPointAndAddressList.add(new SupplyPointAndAddress(punktyPoboruEnergii.get(i), adresList.get(i), kod_pocztowyList.get(i)));
            }

            ModelAndView mav = new ModelAndView("admin/all_supply_points_for_client");
            mav.addObject("SupplyPointsWithAddresses", supplyPointAndAddressList);
            mav.addObject("client_nr", client_nr);
            return mav;
        }


        @RequestMapping("/all_invoices/{client_nr}")
        public ModelAndView showAllInvoices(@PathVariable(name = "client_nr") int client_nr)
        {
            List<Faktura> fakturaList = fakturaDAO.listByClient(client_nr);
            ModelAndView mav = new ModelAndView("admin/all_invoices_for_client");
            mav.addObject("fakturaList", fakturaList);
            return mav;
        }


        @RequestMapping("/all_clients/new")
        public ModelAndView addNewClient()
        {
            ModelAndView mav = new ModelAndView("admin/new_client");
            ClientAndAddress clientAndAddress = new ClientAndAddress();
            mav.addObject("clientAndAddress", clientAndAddress);

            return mav;
        }


        @RequestMapping(value = "/all_clients/new/insert", method = RequestMethod.POST)
        public String insertNewClient(@ModelAttribute("clientAndAddress") ClientAndAddress clientAndAddress)
        {
            try
            {
                kodPocztowyDAO.get(clientAndAddress.kod_pocztowy.getKod_pocztowy());
            }
            catch (EmptyResultDataAccessException exception)
            {
                kodPocztowyDAO.save(clientAndAddress.kod_pocztowy);
            }

            clientAndAddress.adres.setKod_pocztowy(clientAndAddress.kod_pocztowy.getKod_pocztowy()); // set postal code of address
            int nr_adresu = adresDAO.save(clientAndAddress.adres); // add address to DB

            clientAndAddress.klient.setNr_adresu(nr_adresu);

            klientDAO.save(clientAndAddress.klient);
            return "redirect:/all_clients";
        }


        @RequestMapping(value = "/remove_client/{client_nr}")
        public String delete(@PathVariable(name = "client_nr") int client_nr)
        {
            try
            {
                klientDAO.delete(client_nr);
            }
            catch (DataAccessException dataAccessException)
            {
                return "redirect:/all_clients:error";
            }

            return "redirect:/all_clients";
        }


        @RequestMapping(value = "/all_clients:error")
        public ModelAndView showAllClientsAfterError()
        {
            List<Klient> klientList = klientDAO.list();
            ModelAndView mav = new ModelAndView("admin/all_clients_error");
            mav.addObject("klientList", klientList);
            return mav;
        }


        @RequestMapping("/admin_new_supply_point/{client-id}")
        public ModelAndView addNewSupplyPointAdmin(@PathVariable(name = "client-id") int client_id)
        {
            SupplyPointAndAddress supplyPoint = new SupplyPointAndAddress();
            supplyPoint.punktPoboruEnergii.setNr_klienta(client_id);
            ModelAndView mav = new ModelAndView("admin/new_supply_point");
            mav.addObject("supplyPoint", supplyPoint);
            Klient klient = new Klient();
            klient.setNr_klienta(client_id);
            mav.addObject("klient", klient);
            return mav;
        }

        @RequestMapping(value = "/admin_new_supply_point/insert/{client-id}", method = RequestMethod.POST)
        public String insertNewSupplyPointAdmin(@ModelAttribute("supplyPoint") SupplyPointAndAddress supplyPoint, @PathVariable("client-id") int client_id)
        {
            try
            {
                kodPocztowyDAO.get(supplyPoint.kod_pocztowy.getKod_pocztowy());
            }
            catch (EmptyResultDataAccessException exception)
            {
                kodPocztowyDAO.save(supplyPoint.kod_pocztowy);
            }

            supplyPoint.adres.setKod_pocztowy(supplyPoint.kod_pocztowy.getKod_pocztowy()); // set postal code of address
            int nr_adresu = adresDAO.save(supplyPoint.adres); // add address to DB

            supplyPoint.punktPoboruEnergii.setNr_adresu(nr_adresu);
            supplyPoint.punktPoboruEnergii.setNr_klienta(client_id);
            punktPoboruEnergiiDAO.save(supplyPoint.punktPoboruEnergii);
            return "redirect:/admin_supply_points/" + supplyPoint.punktPoboruEnergii.getNr_klienta();
        }

        @RequestMapping(value = "/supply_point/edit/{pt-id}")
        public ModelAndView editSupplyPoint(@PathVariable("pt-id") int pt_id)
        {
            ModelAndView mav = new ModelAndView("/admin/edit_supply_point");

            SupplyPointAndAddress supplyPoint = new SupplyPointAndAddress();

            supplyPoint.punktPoboruEnergii = punktPoboruEnergiiDAO.get(pt_id);
            supplyPoint.adres = adresDAO.get(supplyPoint.punktPoboruEnergii.getNr_adresu());
            supplyPoint.kod_pocztowy = kodPocztowyDAO.get(supplyPoint.adres.getKod_pocztowy());

            mav.addObject("supplyPoint", supplyPoint);

            return  mav;
        }

        @RequestMapping(value = "/supply_point/edit/submit/{pt-id}")
        public String submitEditSupplyPoint(@PathVariable("pt-id") int pt_id, @ModelAttribute("supplyPoint") SupplyPointAndAddress supplyPoint)
        {
            punktPoboruEnergiiDAO.update(supplyPoint.punktPoboruEnergii);
            adresDAO.update(supplyPoint.adres);
            return "redirect:/admin_supply_points/" + supplyPoint.punktPoboruEnergii.getNr_klienta();
        }
    }
}

