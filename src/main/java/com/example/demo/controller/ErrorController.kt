package com.example.demo.controller

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import org.springframework.web.servlet.NoHandlerFoundException
import javax.servlet.http.HttpServletRequest
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.bind.annotation.RequestMapping



@Controller
@ControllerAdvice
class ErrorController {

    @RequestMapping(value = ["errors"], method = [RequestMethod.GET])
    fun renderErrorPage(httpRequest: HttpServletRequest): ModelAndView {

        val errorPage = ModelAndView("errorPage")
        var errorMsg = ""
        val httpErrorCode = getErrorCode(httpRequest)

        when (httpErrorCode) {
            400 -> {
                errorMsg = "Http Error Code: 400. Bad Request"
            }
            401 -> {
                errorMsg = "Http Error Code: 401. Unauthorized"
            }
            404 -> {
                errorMsg = "Http Error Code: 404. Resource not found"
            }
            500 -> {
                errorMsg = "Http Error Code: 500. Internal Server Error"
            }
        }
        errorPage.addObject("errorMsg", errorMsg)
        return errorPage
    }

    private fun getErrorCode(httpRequest: HttpServletRequest): Int {
        return httpRequest
                .getAttribute("javax.servlet.error.status_code") as Int
    }


}



