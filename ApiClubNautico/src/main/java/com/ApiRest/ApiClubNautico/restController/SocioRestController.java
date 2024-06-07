package com.ApiRest.ApiClubNautico.restController;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ApiRest.ApiClubNautico.entities.Socio;
import com.ApiRest.ApiClubNautico.services.SocioServices;

import io.swagger.v3.oas.annotations.Operation;


@CrossOrigin("*")
@RestController
@RequestMapping("socio") // esto es la url
public class SocioRestController {

	@Autowired // inyeccion de dependencia
	private SocioServices socioServices;

	@Operation(summary = "Crear socio")
	@PostMapping // creamos
	public ResponseEntity<Socio> crearSocio(@RequestBody Socio socio) {
		socioServices.createSocio(socio);
		return new ResponseEntity<Socio>(socio, HttpStatus.CREATED);
	}
	@Operation(summary = "Devolver todos los socios")
	@GetMapping
	public ResponseEntity<List<Socio>> getAllSocios() {
		List<Socio> socios = socioServices.findAllSocio();

		return new ResponseEntity<List<Socio>>(socios, HttpStatus.ACCEPTED);
	} // ResponseEntity.ok(socio); este me va a devolver una lista de socio

	@Operation(summary = "Encontrar socio por su id")
    @GetMapping("/findSocioById/{id}") // me devuelve el socio solo
	public ResponseEntity<Socio> encontrarSocioPorId(@PathVariable Long id) {
		 Socio socio = socioServices.findSocioById(id);
	        if (socio != null) {
	            return ResponseEntity.ok(socio);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	   
	}
	@Operation(summary = "Actualizar socio por su id")
	@PutMapping("/{idSocio}") // actualizamos
	public ResponseEntity<Socio> actualizaSocio(@PathVariable Long idSocio, @RequestBody Socio socio) {
		socioServices.updateSocio(idSocio, socio);
		return new ResponseEntity<Socio>(socio, HttpStatus.OK);

	}
	@Operation(summary = "Eliminar socio por su id")
	@DeleteMapping("/{idSocio}") // eliminamos
	public ResponseEntity<?> eliminarSocio(@PathVariable Long idSocio) {
		socioServices.deleteSocioById(idSocio);
		return new ResponseEntity<Socio>(HttpStatus.OK);
	}
	
	
}