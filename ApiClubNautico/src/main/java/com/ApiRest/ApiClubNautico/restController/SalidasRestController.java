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
import com.ApiRest.ApiClubNautico.entities.Patron;
import com.ApiRest.ApiClubNautico.entities.Salidas;
import com.ApiRest.ApiClubNautico.services.SalidasServices;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("salidas")
public class SalidasRestController {

	@Autowired // inyeccion de dependencia
	private SalidasServices salidasServices;

	@Operation(summary = "Crear salida")
	@PostMapping // creamos
	public ResponseEntity<Salidas> crearSalidas(@RequestBody Salidas salidas) {
		System.out.println(salidas);
		salidasServices.createSalidas(salidas);
		return new ResponseEntity<Salidas>(salidas, HttpStatus.CREATED);
	}
	@Operation(summary = "Devolver todas las salidas")
	@GetMapping
	public ResponseEntity<List<Salidas>> getSalidas() {//devuelve todas las salidas disponibles
		List<Salidas> salidas = salidasServices.findAllSalidas();
		return ResponseEntity.ok(salidas);

	}
	@Operation(summary = "Devolver salida por su id")
	@GetMapping("/{idSalidas}") //busca una salida por su id
	public ResponseEntity<Salidas> buscarSalidasPorId(@PathVariable Long idSalidas) {
		Salidas salidas = salidasServices.findSalidas(idSalidas);
		return new ResponseEntity<>(salidas, HttpStatus.OK);

	}
	@Operation(summary = "Actualizar salida por su id")
	@PutMapping("/{idSalidas}") // actualizamos
	public ResponseEntity<Salidas> actualizaSalidas(@PathVariable Long idSalidas, @RequestBody Salidas salidas) {
		salidasServices.updateSalidas(idSalidas, salidas);
		return new ResponseEntity<Salidas>(salidas, HttpStatus.OK);

	}
	@Operation(summary = "Asignar patron y barco a una salida")
	@PutMapping("/{idSalidas}/{idPatron}/{idBarco}")//asigno un patron y un barco a una salida
	public ResponseEntity<?> añadirPatronYBarcoaSalida(@PathVariable Long idSalidas, @PathVariable Long idPatron,
			@PathVariable Long idBarco) {
		salidasServices.añadirPatronYBarcoaSalida(idPatron, idBarco, idSalidas);
		Salidas salida = salidasServices.findSalidas(idSalidas);
		return new ResponseEntity<Salidas>(salida, HttpStatus.OK);

	}
	@Operation(summary = "Eliminar salida por su id")
	@DeleteMapping("/{idSalidas}") // eliminamos
	public ResponseEntity<?> eliminarSalidas(@PathVariable Long idSalidas) {
		salidasServices.deleteSalidasById(idSalidas);
		return new ResponseEntity<Patron>(HttpStatus.OK);
	}

}
