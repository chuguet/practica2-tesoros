//package com.movember.quizz.model.test;
//
//import javax.inject.Inject;
//import org.junit.Test;
//import org.springframework.test.context.transaction.TransactionConfiguration;
//import org.springframework.transaction.annotation.Transactional;
//import com.movember.quizz.model.service.IEncuestaService;
//import com.movember.quizz.model.service.IUsuarioService;
//
//@TransactionConfiguration(defaultRollback = true)
//@Transactional
//public class ServiceUsuarioTest extends AbstractTest {
//
//	@Inject
//	private IUsuarioService usuarioService;
//
//	@Inject
//	private IEncuestaService encuestaService;
//
//	@Test
//	@Transactional
//	public void testSave() {
//		// Usuario usuario = new Usuario("Carlos", "Huguet",
//		// "huguet10@gmail.com", "1234", "1234", 1);
//		// List<Usuario> usuarios = new ArrayList<Usuario>();
//		// usuarios.add(usuario);
//		//
//		// List<Encuesta> encuestas = new ArrayList<Encuesta>();
//		// Encuesta encuesta = new Encuesta();
//		// encuesta.setNombre("Encuesta de Ralph Wiggum");
//		// encuesta.setUsuarios(usuarios);
//		// encuestas.add(encuesta);
//		// usuario.setEncuestas(encuestas);
//		//
//		// Pregunta pregunta = new Pregunta();
//		// pregunta.setEncuesta(encuesta);
//		// pregunta.setPregunta("For president?");
//		// List<Pregunta> preguntas = new ArrayList<Pregunta>();
//		// preguntas.add(pregunta);
//		// encuesta.setPreguntas(preguntas);
//		//
//		// usuarioService.save(usuario);
//		// Long idEncuesta = usuarioService.findOne(new
//		// Long(1)).getEncuestas().iterator().next().getId();
//		// Long idPregunta =
//		//
//		// encuestaService.findOne(idEncuesta).getPreguntas().iterator().next().getId();
//		// assertEquals(idEncuesta, idPregunta);
//	}
//
//}
