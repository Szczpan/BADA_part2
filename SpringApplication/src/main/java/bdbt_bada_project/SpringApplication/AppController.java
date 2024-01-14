package bdbt_bada_project.SpringApplication;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

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

        registry.addViewController("/main_user").setViewName("user/main_user");
        registry.addViewController("/user_info").setViewName("user/user_info");
        registry.addViewController("/supply_points").setViewName("user/supply_points");
        registry.addViewController("/invoices").setViewName("user/invoices");

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
    }
}

