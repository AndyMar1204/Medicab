package com.andy.Medicab.controller;

import com.andy.Medicab.model.Position;
import com.andy.Medicab.services.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.andy.Medicab.controller.Outils.*;

/**
 * @author Ir Andy
 */
@CrossOrigin({ BASE_URL })
@RestController
@RequestMapping("/rest/position/")
public class PositionController implements Crud<Position, Long> {
	@Autowired
	private PositionService service;

	@GetMapping(SAVE_)
	@Override
	public ResponseEntity<Long> save(@RequestBody Position position) {
		try {
			return new ResponseEntity<Long>(service.save(position), HttpStatus.OK);
		} catch (Exception ex) {
			return buildErrorMessage(ex, "Echec du sauvegarde de la position");
		}
	}

	@PutMapping(UPDATE_ + "/{id}")
	@Override
	public ResponseEntity<Position> update(@RequestBody Position position, @PathVariable Long id) {
		try {
			if (service.existById(id)) {
				return new ResponseEntity<Position>(service.update(position), HttpStatus.OK);
			} else
				return buildErrorMessage("Cette position n'existe pas");
		} catch (Exception e) {
			return buildErrorMessage(e, "Impossible de mettre à jour la position");
		}
	}

	@DeleteMapping(DELETE_BY_ID + "{id}")
	@Override
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		try {
			service.deleteById(id);
			return buildSuccessMessage("Position effacée avec success");
		} catch (Exception ex) {
			return buildErrorMessage(ex, "Erreur lors de la suppression de la position");
		}
	}

	@GetMapping(FIND_BY_ID + "¨{id}")
	@Override
	public ResponseEntity<Position> findById(@PathVariable Long id) {
		try {
			return new ResponseEntity<Position>(service.findById(id), HttpStatus.OK);
		} catch (Exception ex) {
			return buildErrorMessage(ex, "Aucune position trouvé");
		}
	}

	@GetMapping(FIND_ALL)
	@Override
	public ResponseEntity<List<Position>> getAll() {
		try {
			return new ResponseEntity<List<Position>>(service.findAll(), HttpStatus.OK);
		} catch (Exception ex) {
			return buildErrorMessage(ex, "Une erreur s'est produite");
		}
	}

	@GetMapping(path = CHECK_EXIST_BY_ID + "{id}")
	@Override
	public ResponseEntity<Boolean> checkExist(@PathVariable Long id) {
		try {
			return new ResponseEntity<Boolean>(service.existById(id), HttpStatus.OK);
		} catch (Exception ex) {
			return buildErrorMessage(ex, "Une erreur est survenue lors du traitement");
		}
	}
}
