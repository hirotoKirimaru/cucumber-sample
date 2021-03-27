package kirimaru.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DefultController {

    /**
     * index
     */
    @GetMapping(path = "/index")
    public ModelAndView index(ModelAndView mav)  {

        throw new RuntimeException("ごりら");

//         messageに値を設定
//        mav.addObject("message", "hello world");
//        mav.setViewName("index");
//        return mav;
    }
}
