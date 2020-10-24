package com.bolsadeideas.springboot.app.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.bolsadeideas.springboot.app.models.entity.Cliente;
import com.bolsadeideas.springboot.app.models.service.IClienteService;
import com.bolsadeideas.springboot.app.util.paginator.PageRender;


import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@SessionAttributes("cliente")
public class ClienteController {

	@Autowired
	private IClienteService clienteService;
	private final Logger log=org.slf4j.LoggerFactory.getLogger(getClass());
	
	@RequestMapping(value="/uploads/{filename:.+}")
	public ResponseEntity<Resource>verFoto(@PathVariable String filename){
		Path pathFoto=Paths.get("uploads").resolve(filename).toAbsolutePath();
		log.info("pathFoto"+pathFoto);
		Resource recurso=null;
		
		try {
			recurso=new UrlResource(pathFoto.toUri());
			if(!recurso.exists()||!recurso.isReadable()) {
				throw new RuntimeException("Error no se puede cargar la imagen"+pathFoto);
				
			}
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION,"attachement;filename=\""+recurso.getFilename()+"\"")
				.body(recurso);
		
	}
	
	
	@GetMapping(value="/ver/{id}")
	public String ver(@PathVariable(value="id")Long id,Map<String,Object>model,RedirectAttributes flash){
		Cliente cliente=clienteService.findOne(id);
		if(cliente==null) {
			flash.addFlashAttribute("error","El cliente no existe en la base de datos");
			return"redirerct/listar";
		}
		model.put("cliente",cliente);
		model.put("titulo","Detalle nombre del cliente   "+cliente.getNombre());
	
		
		return"ver";
		
	}

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(@RequestParam(name="page" ,defaultValue="0")int page, Model model) {
		Pageable pageRequest=PageRequest.of(page, 4);
		
		Page<Cliente>clientes=clienteService.findAll(pageRequest);
		PageRender<Cliente> pageRender = new PageRender<Cliente>("/listar", clientes);
		model.addAttribute("titulo", "Listado de clientes");
		model.addAttribute("page", pageRender);
		model.addAttribute("clientes", clientes);
		return "listar";
	}
	
	@RequestMapping(value = "/form")
	public String crear(Map<String, Object> model) {

		Cliente cliente = new Cliente();
		model.put("cliente", cliente);
		model.put("titulo", "Formulario de Cliente");
		return "form";
	}
	
	@RequestMapping(value="/form/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model,RedirectAttributes flash) {
		
		Cliente cliente = null;
		
		if(id > 0) {
			cliente = clienteService.findOne(id);
			
			if(cliente==null) {
				flash.addAttribute("error", "Cliente no existe en la base de datos");
				return "redirect:/listar";
			}
			
		} else {
			flash.addFlashAttribute("error", "Cliente no puede ser 0");
			return "redirect:/listar";
		}
		model.put("cliente", cliente);
		model.put("titulo", "Editar Cliente");
		return "form";
	}
	
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model,@RequestParam("file")MultipartFile foto,RedirectAttributes flash, SessionStatus status) {
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Cliente");
			return "form";
		}
		
		if(!foto.isEmpty()) {
/*		Path directorioRecursos = Paths.get("src//main//resources//static//uploads");
			String rootPath= directorioRecursos.toFile().getAbsolutePath();*/
			
		String uniqueFilename=UUID.randomUUID().toString()+"_"+	foto.getOriginalFilename();
		Path rootPath= Paths.get("uploads").resolve(uniqueFilename);
		Path rootAbsolutPath=rootPath.toAbsolutePath();	
			
			log.info("rootPath"+rootPath);
			log.info("rootAbsolutPath"+rootAbsolutPath);
			
			
			
			try {
//				byte[]bytes=foto.getBytes();
//				Path rutaCompleta=Paths.get(rootPath+"//"+foto.getOriginalFilename());
//				Files.write(rutaCompleta,bytes);
				Files.copy(foto.getInputStream(), rootAbsolutPath);
				
				flash.addFlashAttribute("info","Has subido correctamente la foto:'"+uniqueFilename+"'");
				cliente.setFoto(uniqueFilename);
				
			} catch (IOException e) {
		
				e.printStackTrace();
			}
			
		}
		
		String mensajeFlash=(cliente.getId()!=null)?"Cliente editado con éxito":"Cliente creado con éxito";
		
		clienteService.save(cliente);
		flash.addFlashAttribute("success", mensajeFlash);
		System.out.println("CLIENTE CREADO EXITOSAMENTEEEEE");
		status.setComplete();
		return "redirect:listar";
	}
	
	@RequestMapping(value="/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Long id,RedirectAttributes flash) {
		
		if(id > 0) {
			clienteService.delete(id);
			flash.addFlashAttribute("error", "Cliente eliminado exitosamente");
		}
		return "redirect:/listar";
	}
}