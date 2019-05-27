package services;

import java.io.FileOutputStream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ActorRepository;
import security.LoginService;
import security.UserAccount;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import domain.Actor;
import domain.Moderador;
import domain.Periodista;
import domain.Usuario;

@Service
@Transactional
public class ActorService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private ActorRepository actorRepository;

	// Constructors -----------------------------------------------------------

	public ActorService() {
		super();
	}

	// CRUD methods

	public void deleteActor(Actor actor){
		this.actorRepository.delete(actor);
	}

	// Other business methods -------------------------------------------------

	public Actor findByPrincipal() {
		Actor result = this.findByUserAccount(LoginService.getPrincipal());
		return result;
	}

	private Actor findByUserAccount(UserAccount userAccount) {
		Assert.notNull(userAccount);
		Actor result = this.actorRepository.findByUserAccountId(userAccount.getId());
		Assert.notNull(result);

		return result;
	}

	public Actor findByUsername(String username){
		Actor result = this.actorRepository.findOneByName(username);
		return result;
	}



	/*************************************
	 * Persolnal Data PDF
	 *************************************/
	public Document generatePersonalInformationPDF(Actor actor, String path) {
		Document doc = null;
		try {
			// Creating a Document
			doc = new Document();
			PdfWriter.getInstance(doc, new FileOutputStream(path));
			doc.open();

			// Creating a table
			float[] pointColumnWidths = {
				150F, 150F
			};
			PdfPTable table = new PdfPTable(pointColumnWidths);

			// Adding cells to the table
			PdfPCell cell = new PdfPCell(new Phrase("Informacion personal de " + actor.getNombre()));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setColspan(2);
			table.addCell(cell);

			table.addCell(new PdfPCell(new Phrase("Nombre")));
			table.addCell(new PdfPCell(new Phrase(actor.getNombre())));
			table.addCell(new PdfPCell(new Phrase("Apellidos")));
			table.addCell(new PdfPCell(new Phrase(actor.getApellidos())));
			table.addCell(new PdfPCell(new Phrase("Email")));
			table.addCell(new PdfPCell(new Phrase(actor.getEmail())));

			if (actor instanceof Usuario) {
				Usuario usuario = (Usuario) actor;
				table.addCell(new PdfPCell(new Phrase("Estatus")));
				table.addCell(new PdfPCell(new Phrase(usuario.getEstatus().getLabel())));
			}

			if (actor instanceof Periodista) {
				Periodista periodista = (Periodista) actor;
				table.addCell(new PdfPCell(new Phrase("Foto")));
				table.addCell(new PdfPCell(new Phrase(periodista.getFoto())));
				table.addCell(new PdfPCell(new Phrase("Email paypal")));
				table.addCell(new PdfPCell(new Phrase(periodista.getCartera().getPaypalEmail())));
			}

			if (actor instanceof Moderador) {
				Moderador periodista = (Moderador) actor;
				table.addCell(new PdfPCell(new Phrase("Email paypal")));
				table.addCell(new PdfPCell(new Phrase(periodista.getCartera().getPaypalEmail())));
			}

			// Adding Table to document
			doc.add(table);

			// Closing the document
			doc.close();
		} catch (Exception e) {
			System.out.println(e.getClass());
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			doc.close();
		}

		return doc;
	}

}