package com.bolsadeideas.springboot.app.controllers;

import java.io.IOException;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bolsadeideas.springboot.app.models.entity.Cliente;
import com.bolsadeideas.springboot.app.models.entity.Producto;
import com.bolsadeideas.springboot.app.models.service.IClienteService;

@Controller
public class RegistrarProductoController {
	
	@Autowired
	private IClienteService clienteService;
	
	
	@RequestMapping(value = "/productoReg")
	public String crear(Map<String, Object> model) {

		Producto producto = new Producto();
		model.put("producto", producto);
		model.put("titulo", "Formulario de Producto");
		clienteService.savePro(producto);
		
		return "producto/registrarProducto";
	}
	
	@RequestMapping(value = "/formularioRegistro", method = RequestMethod.POST)
	public String guardar(@Valid Producto producto, BindingResult result, Model model,
			 RedirectAttributes flash, SessionStatus status) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Cliente");
			return "producto/registrarProducto";
		}

		String mensajeFlash = (producto.getId() != null) ? "Cliente editado con éxito!" : "Cliente creado con éxito!";

		clienteService.savePro(producto);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:listar";
	}

}
