package com.movember.treasure.controller.dto;

import java.util.ArrayList;
import java.util.List;

import com.movember.treasure.model.bean.Estadistica;
import com.movember.treasure.model.bean.HitoEstadistica;
import com.movember.treasure.model.exception.AppException;

/**
 * The Class EstadisticaDTO.
 */
public class EstadisticaDTO extends AbstractDTO {

	/** The contador_total. */
	private Integer contador_total;

	/** The fecha_fin. */
	private String fecha_fin;

	/** The fecha_inicio. */
	private String fecha_inicio;

	/** The id_encuesta. */
	private List<HitoEstadisticaDTO> hitos;

	/** The codigo. */
	private String ruta;

	@Override
	public void toRest(Object object) throws AppException {
		Estadistica estadistica = (Estadistica) object;

		this.setRuta(estadistica.getRuta());
		this.setId(estadistica.getId());
		this.setContador_total(estadistica.getContador_total());
		this.setFecha_fin(estadistica.getFecha_fin().toString());
		this.setFecha_inicio(estadistica.getFecha_inicio().toString());
		if (estadistica.getHitos() != null && estadistica.getHitos().size() > 0) {
			HitoEstadisticaDTO hitoEstadisticaDTO;
			for (HitoEstadistica hito : estadistica.getHitos()) {
				hitoEstadisticaDTO = new HitoEstadisticaDTO();
				hitoEstadisticaDTO.toRest(hito);
				this.addHitoEstadisticaDTO(hitoEstadisticaDTO);
			}
		}
	}

	private void addHitoEstadisticaDTO(HitoEstadisticaDTO hitoEstadisticaDTO) {
		if (this.getHitos() == null) {
			this.hitos = new ArrayList<HitoEstadisticaDTO>();
		}
		hitos.add(hitoEstadisticaDTO);
	}

	public Integer getContador_total() {
		return contador_total;
	}

	public void setContador_total(Integer contador_total) {
		this.contador_total = contador_total;
	}

	public String getFecha_fin() {
		return fecha_fin;
	}

	public void setFecha_fin(String fecha_fin) {
		this.fecha_fin = fecha_fin;
	}

	public String getFecha_inicio() {
		return fecha_inicio;
	}

	public void setFecha_inicio(String fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	public List<HitoEstadisticaDTO> getHitos() {
		return hitos;
	}

	public void setHitos(List<HitoEstadisticaDTO> hitos) {
		this.hitos = hitos;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.movember.quizz.controller.dto.AbstractDTO#toBusiness(java.lang.Object
	 * )
	 */
	@Override
	public void toBusiness(Object object) throws AppException {
	}

}
