package taxi.controller.manufacturer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import taxi.lib.Injector;
import taxi.model.Manufacturer;
import taxi.service.ManufacturerService;

public class AddManufacturerController extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(AddManufacturerController.class);
    private static final Injector injector = Injector.getInstance("taxi");
    private final ManufacturerService manufacturerService = (ManufacturerService) injector
            .getInstance(ManufacturerService.class);

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/manufacturers/add.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String country = req.getParameter("country");
        Manufacturer manufacturer = new Manufacturer(name, country);
        manufacturerService.create(manufacturer);
        logger.info("manufacturer was successfully added. Params: id - {}, name - {}, "
                        + "country - {}",
                manufacturer.getId(), manufacturer.getName(), manufacturer.getCountry());
        resp.sendRedirect(req.getContextPath() + "/manufacturers/add");
    }
}
