package com.movember.quizz.controller.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import com.movember.treasure.model.bean.Hito;
import com.movember.treasure.model.bean.Ruta;
import com.movember.treasure.model.exception.AppException;

/**
 * The Class RutaDTO.
 */
public class RutaDTO extends AbstractDTO {

	/** The nombre. */
	private String nombre;

	/** The fecha_inicio. */
	private String fecha_inicio;

	/** The fecha_fin. */
	private String fecha_fin;

	/** The hitos dto. */
	private List<HitoDTO> hitosDTO;

	/**
	 * Instantiates a new ruta dto.
	 */
	public RutaDTO() {
		hitosDTO = new ArrayList<HitoDTO>();
	}

	/**
	 * Gets the nombre.
	 * 
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Sets the nombre.
	 * 
	 * @param nombre
	 *            the new nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Gets the fecha_inicio.
	 * 
	 * @return the fecha_inicio
	 */
	public String getFecha_inicio() {
		return fecha_inicio;
	}

	/**
	 * Sets the fecha_inicio.
	 * 
	 * @param fecha_inicio
	 *            the new fecha_inicio
	 */
	public void setFecha_inicio(String fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	/**
	 * Gets the fecha_fin.
	 * 
	 * @return the fecha_fin
	 */
	public String getFecha_fin() {
		return fecha_fin;
	}

	/**
	 * Sets the fecha_fin.
	 * 
	 * @param fecha_fin
	 *            the new fecha_fin
	 */
	public void setFecha_fin(String fecha_fin) {
		this.fecha_fin = fecha_fin;
	}

	/**
	 * Gets the hitos dto.
	 * 
	 * @return the hitos dto
	 */
	public List<HitoDTO> getHitosDTO() {
		return hitosDTO;
	}

	/**
	 * Sets the hitos dto.
	 * 
	 * @param hitosDTO
	 *            the new hitos dto
	 */
	public void setHitosDTO(List<HitoDTO> hitosDTO) {
		this.hitosDTO = hitosDTO;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.movember.quizz.controller.dto.AbstractDTO#toRest(java.lang.Object)
	 */
	@Override
	public void toRest(Object object) throws AppException {
		Ruta ruta = (Ruta) object;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		this.setId(ruta.getId());
		this.nombre = ruta.getNombre();
		this.fecha_inicio = sdf.format(ruta.getFecha_inicio());
		this.fecha_fin = sdf.format(ruta.getFecha_fin());

		if (ruta.getHitos() != null && ruta.getHitos().size() > 0) {
			for (Hito hito : ruta.getHitos()) {
				HitoDTO hitoDTO = new HitoDTO();
				hitoDTO.toRest(hito);
				this.hitosDTO.add(hitoDTO);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.movember.quizz.controller.dto.AbstractDTO#toBusiness(java.lang.Object
	 * )
	 */
	@Override
	public void toBusiness(Object object) throws AppException {
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
		Ruta ruta = (Ruta) object;
		ruta.setId(this.getId());
		ruta.setNombre(this.nombre);
		try {
			ruta.setFecha_inicio(formatoDelTexto.parse(this.fecha_inicio));
			ruta.setFecha_fin(formatoDelTexto.parse(this.fecha_fin));
		}
		catch (ParseException e) {
			throw new AppException("Error en la conversión de fechas");
		}

		if (this.hitosDTO != null && this.hitosDTO.size() > 0) {
			for (HitoDTO hitoDTO : this.hitosDTO) {
				Hito hito = new Hito();
				hito.setId_ruta(this.getId());
				hitoDTO.toBusiness(hito);
				ruta.getHitos().add(hito);
			}
		}
	}
}
