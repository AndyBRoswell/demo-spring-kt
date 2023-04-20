package top.abr.demo_spring_kt

import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DemoSpringKtApplication

fun main(args: Array<String>) {
    runApplication<DemoSpringKtApplication>(*args) {
        setBannerMode(Banner.Mode.OFF)
    }
}
