package com.ApiRest.ApiClubNautico.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ApiRest.ApiClubNautico.entities.Barco;
import com.ApiRest.ApiClubNautico.services.BarcoServices;

import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping("barco")
public class BarcoRestController {

	@Autowired // inyeccion de dependencia
	private BarcoServices barcoServices;
	@Operation(summary = "Crear barco")
	@PostMapping // creamos
	public ResponseEntity<Barco> crearBarco(@RequestBody Barco barco) {
		barcoServices.createBarco(barco);
		return new ResponseEntity<Barco>(barco, HttpStatus.CREATED);
	}
	@Operation(summary = "Devolver todos los barcos")
	@GetMapping // lista complta de barcos
	public ResponseEntity<List<Barco>> listaCompletaBarcos() {
		return new ResponseEntity<>(barcoServices.findAllBarco(), HttpStatus.ACCEPTED);
	}
	@Operation(summary = "Devolver todos los barcos de un id")
	@GetMapping("{idSocio}/listaBarcosPorSocioId") // me devuelve lista barcos asociado a un id
	public ResponseEntity<List<Barco>> getBarcoByIdSocio(@PathVariable Long idSocio) {

		return new ResponseEntity<>(barcoServices.listaBarcosByIdSocio(idSocio), HttpStatus.OK);
	}
	@Operation(summary = "Devolver barco por su id")
	@GetMapping("/findBarco/{id}") // me devuelve un barco buscado por su id
	public ResponseEntity<Barco> findBarcoById(@PathVariable Long id) {
		Barco barco = barcoServices.findBarco(id);
		return ResponseEntity.ok(barco);
	}
	@Operation(summary = "Actualizar barco por su id")
	@PutMapping("/{idBarco}") // actualizamos
	public ResponseEntity<Barco> actualizaBarco(@PathVariable Long idBarco, @RequestBody Barco barco) {
		barcoServices.updateBarco(idBarco, barco);
		return new ResponseEntity<Barco>(barco, HttpStatus.OK);

	}
	@Operation(summary = "Eliminar barco por su id")
	@DeleteMapping("/{idBarco}") // eliminamos
	public ResponseEntity<?> eliminarBarco(@PathVariable Long idBarco) {
		barcoServices.deleteBarcoById(idBarco);
		return new ResponseEntity<Barco>(HttpStatus.OK);
	}
	@Operation(summary = "Asignar un barco con su id a un socio con su id")
	@PutMapping("/{idBarco}/socio/{idSocio}") // asi le voy asociar un barco a un socio
	public ResponseEntity<?> añadirBarcoASocio(@PathVariable Long idBarco, @PathVariable Long idSocio) {
		barcoServices.añadirBarcoaSocio(idSocio, idBarco);
	    return new ResponseEntity<>(HttpStatus.OK);
	
	}
}
