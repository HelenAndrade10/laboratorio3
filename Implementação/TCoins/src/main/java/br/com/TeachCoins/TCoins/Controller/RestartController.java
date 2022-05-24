package br.com.TeachCoins.TCoins.Controller;

import br.com.TeachCoins.TCoins.TCoinsApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestartController {

    @PostMapping("/restart")
    public void restart() {
        TCoinsApplication.restart();
    }

}
