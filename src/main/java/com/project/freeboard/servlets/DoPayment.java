package com.project.freeboard.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.server.spi.response.NotFoundException;
import com.project.freeboard.entity.Auctions;
import com.project.freeboard.entity.Companies;
import com.project.freeboard.entity.Offers;
import com.project.freeboard.entity.Students;
import com.project.freeboard.entity.Transactions;
import com.project.freeboard.message.SendEmailMessage;
import com.project.freeboard.service.EpCompany;
import com.project.freeboard.service.EpOffer;
import com.project.freeboard.service.EpTransaction;
import com.project.freeboard.service.Freeboard;

//@WebServlet(
//	    name = "HelloAppEngine",
//	    urlPatterns = {"/hello"}
//	)

/**
 * Modulo para validar y registrar pagos
 * @author Ricardo
 *
 */
public class DoPayment extends HttpServlet {

	public final static String STATE_POL_APPROVED = "4";
	public final static String RESPONSE_MESSAGE_POL_APPROVED = "APPROVED";
	public final static String RESPONSE_CODE_POL_APPROVED = "1";
	public final static String STUDENT = "freeboarder";
	public final static String US = "Freeboard";

	private EpTransaction t;
	private SendEmailMessage sem;
	private EpOffer o;
	private Freeboard freeboard;
	private EpCompany epCompany;

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String reference_code = req.getParameter("reference_sale"); // idTransaccion
		String description = req.getParameter("description");
		String amount = req.getParameter("value"); // value en la respuesta
		String tax = req.getParameter("tax");
		String tax_return_base = req.getParameter("tax_return_base");
		String currency = req.getParameter("currency");
		String buyer_full_name = req.getParameter("buyer_full_name");
		String buyer_email = req.getParameter("buyer_email");
		String test = req.getParameter("test");
		String pay_hash = req.getParameter("extra1"); // extra1
		String id_oferta = req.getParameter("extra2"); // extra2
		String response_code_pol = req.getParameter("response_code_pol");
		String state_pol = req.getParameter("state_pol");
		String response_message_pol = req.getParameter("response_message_pol");
		String payment_method_type = req.getParameter("payment_method_type");
		String transaction_date = req.getParameter("transaction_date");
		String payment_method_name = req.getParameter("payment_method_name");
		
		
		System.out.println("Amount: " + amount);
		freeboard= new Freeboard();
		
		try {
			Companies companies= epCompany.getCompanyByName(amount);
			System.out.println(companies.getHash());
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		t = new EpTransaction();
		o = new EpOffer();

		try {
			
			// Valida si se trata de una operación real
			boolean validatedPayment = validatedPayment(reference_code, pay_hash, test);

			boolean updated = false;

			boolean isTransactionApproved = false;
			boolean isTransactionPending = false;

			boolean sendMessageToConfirmPayment = false;
			boolean sendMessageToShareContactWithStudent = false;
			boolean sendMessageToShareContactWithBusiness = false;
			
			if (validatedPayment) {
				updated = updatedModel(reference_code, response_code_pol, state_pol, response_message_pol, payment_method_type,
						transaction_date, payment_method_name);
			}

			if (updated) {
				isTransactionApproved = isTransactionApproved(state_pol, response_code_pol, response_message_pol);
			}

			String studentEmail = consultStudentEmail(id_oferta);
			String businessEmail = consultBusinessEmail(id_oferta);
			
			if (isTransactionApproved) {	
				sendMessageToConfirmPayment = sendMessageToConfirmPayment(id_oferta, businessEmail, amount, tax);
				sendMessageToShareContactWithStudent = sendMessageToShareContactWithStudent(id_oferta, studentEmail, businessEmail);
				sendMessageToShareContactWithBusiness = sendMessageToShareContactWithBusiness(id_oferta, businessEmail, studentEmail);
			} else{
				
				isTransactionPending = isTransactionPending();
			}

		} catch (ParseException | NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private boolean sendMessageToConfirmPayment(String id_oferta, String businessEmail, String amount, String tax) throws NotFoundException {
		sem = new SendEmailMessage();
		String companyName = consultCompanyName(id_oferta);
		String companyMainContact = consultCompanyMainContact(id_oferta);
		String companyEmail = businessEmail;
		String projectName = consultProjectName(id_oferta);
		double subtotal = Double.parseDouble(amount) - Double.parseDouble(tax);

		String subject = companyName +", tu pago ha sido confirmado";
		
		String message = "Estimado , "+ companyMainContact +" \n \n "
				+ "Tu pago ha sido confirmado.  \n \n "
				+ "PROYECTO: " + projectName +" \n \n "
				+ "SUBTOTAL: " + subtotal +" \n "
				+ "IVA: " + tax +" \n "
				+ "TOTAL: " + amount +" \n \n "
				+ "En pocos minutos recibirás un mensaje con la información del "+ STUDENT + " que ganó tu subasta."
				+ "Muchas gracias por confiar en " + US;

		boolean messageToBusiness = sem.sendMessage(companyEmail, subject, message);

		if( messageToBusiness )
			return true;
		else
			return false;
	}

	/**
	 * Envía un mensaje de correo electrónico al estudiante para compartir la información de contacto de la empresa.
	 * @param emailStudent
	 * @param businessEmail
	 * @return
	 * @throws NotFoundException 
	 */
	private boolean sendMessageToShareContactWithStudent(String id_oferta, String emailStudent, String businessEmail) throws NotFoundException {
		sem = new SendEmailMessage();
		String studentName = consultStudentName(id_oferta);
		String companyName = consultCompanyName(id_oferta);
		String companyMainContact = consultCompanyMainContact(id_oferta);
		String companyEmail = businessEmail;
		String companyPhone = consultCompanyPhone(id_oferta);
		String projectName = consultProjectName(id_oferta);

		String subject = studentName +", tu oferta ha sido aprobada";

		String message = "Estimado/a "+ studentName +"\n \n"
				+ "La empresa "+ companyName +" ha aceptado tu oferta. Es momento de iniciar a trabajar en el proyecto. \n \n"
				+ "Puedes acceder a la información del proyecto desde tu panel de usuario. \n \n"
				+ "Los datos de contacto de la empresa son los siguientes: \n \n"
				+ "- NOMBRE EMPRESA: " + companyName + " \n \n"
				+ "- PERSONA DE CONTACTO: " + companyMainContact + " \n \n"
				+ "- EMAIL: " + companyEmail + "\n \n"
				+ "- TELÉFONO: " + companyPhone + "\n \n"
				+ "- NOMBRE PROYECTO: " + projectName + "\n \n";

		boolean messageToStudent = sem.sendMessage(emailStudent, subject, message);

		if( messageToStudent )
			return true;
		else
			return false;
	}

	/**
	 * Envía un mensaje a la empresa con los datos de contacto del estudiante
	 * @param emailBusiness
	 * @return
	 * @throws NotFoundException 
	 */
	private boolean sendMessageToShareContactWithBusiness(String id_oferta, String businessEmail, String emailStudent) throws NotFoundException {

		sem = new SendEmailMessage();
		String studentName = consultStudentName(id_oferta);
		String studentLast = consultStudentLast(id_oferta);
		String studentPhone = consultStudentPhone(id_oferta);
		String companyName = consultCompanyName(id_oferta);
		String companyMainContact = consultCompanyMainContact(id_oferta);
		String companyEmail = businessEmail;
		String projectName = consultProjectName(id_oferta);

		String subject = companyName +", "+studentName +" va a realizar tu proyecto " +"\"" + projectName + "\"";
		
		String message = "Estimado , "+ companyMainContact +" \n \n "
				+ "El freeboarder, "+ studentName +" , está dispuesto a realizar tu proyecto de "+ companyName +", \"" + projectName + "\"" +" \n \n "
				+ "Es importante que te comuniques con él, para acordar detalles, formas y tiempos de entrega. \n \n "
				+ "Los datos de contacto del freeboarder son los siguientes: \n \n "
				+ "NOMBRE: " + studentName + " " + studentLast + "\n \n"
				+ "EMAIL: " + emailStudent + "\n \n"
				+ "TELÉFONO: " + studentPhone + "\n \n"
				+ "NOMBRE DEL PROYECTO: " + projectName;

		boolean messageToBusiness = sem.sendMessage(companyEmail, subject, message);

		if( messageToBusiness )
			return true;
		else
			return false;
	}

	/**
	 * Consulta en la base de datos el email de la empresa
	 * @return
	 * @throws NotFoundException 
	 */
	private String consultBusinessEmail(String id_oferta) throws NotFoundException {
		Auctions auction = o.getAuctionsIdauctions(id_oferta);
		Companies company = auction.getCompaniesId();
		String companyEmail = company.getEmail();
		return companyEmail;
	}

	/**
	 * Consulta en la base de datos el email del estudiante
	 * @param reference_code 
	 * @return
	 * @throws NotFoundException 
	 */
	private String consultStudentEmail(String id_oferta) throws NotFoundException {
		Students student = o.getStudentId(id_oferta);
		return student.getEmail();
	}

	/**
	 * Consulta en la base de datos el nombre de la subasta
	 * @return
	 * @throws NotFoundException 
	 */
	private String consultProjectName(String id_oferta) throws NotFoundException {
		Auctions auctions = o.getAuctionsIdauctions(id_oferta);
		return auctions.getName();
	}

	/**
	 * Consulta en la base de datos el número telefónico de la compañía
	 * @return
	 * @throws NotFoundException 
	 */
	private String consultCompanyPhone(String id_oferta) throws NotFoundException {
		return o.getAuctionsIdauctions(id_oferta).getCompaniesId().getPhone();
	}

	/**
	 * Consulta en la base de datos el nombre de la persona de contacto de la compañía
	 * @return
	 * @throws NotFoundException 
	 */
	private String consultCompanyMainContact(String id_oferta) throws NotFoundException {
		return o.getAuctionsIdauctions(id_oferta).getCompaniesId().getContactPerson();
	}

	/**
	 * Consulta en la base de datos el nombre de la compañía
	 * @return
	 * @throws NotFoundException 
	 */
	private String consultCompanyName(String id_oferta) throws NotFoundException {
		return o.getAuctionsIdauctions(id_oferta).getCompaniesId().getName();
	}

	/**
	 * Consulta en la base de datos el nombre del estudiante
	 * @return
	 * @throws NotFoundException 
	 */
	private String consultStudentName(String id_oferta) throws NotFoundException {
		return o.getStudentId(id_oferta).getName();
	}
	
	/**
	 * Consulta en la base de datos el apellido del estudiante
	 * @param id_oferta
	 * @return
	 * @throws NotFoundException 
	 */
	private String consultStudentLast(String id_oferta) throws NotFoundException {
		return o.getStudentId(id_oferta).getLastname();
	}
	
	/**
	 * Consulta en la base de datos el número telefónico del estudiante
	 * @param id_oferta
	 * @return
	 * @throws NotFoundException
	 */
	private String consultStudentPhone(String id_oferta) throws NotFoundException {
		return o.getStudentId(id_oferta).getPhone();
	}

	/**
	 * Verifica si la transacción realizada por la empresa fue aprobada desde el gateway de pagos
	 * @param state_pol
	 * @param response_code_pol
	 * @param response_message_pol
	 * @return
	 */
	private boolean isTransactionApproved(String state_pol, String response_code_pol, String response_message_pol) {

		if (state_pol.equals(STATE_POL_APPROVED) && response_code_pol.equals(RESPONSE_CODE_POL_APPROVED)
				&& response_message_pol.equals(RESPONSE_MESSAGE_POL_APPROVED))
			return true;

		else
			return false;
	}
	
	/**
	 * Verifica si la transacción realizada por la empresa está pendiente
	 * @return
	 */
	private boolean isTransactionPending() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Consulta cuál es el estado o respuesta del gateway de pagos de una trasacción realizada por la empresa
	 * @return
	 */
	private String checkTransactionStatus() {
		return null;
	}

	/**
	 * Utiliza el hash para verificar si una trasacción es válida.
	 * @param pay_hash
	 * @param test
	 * @return
	 * @throws NotFoundException 
	 */
	private boolean validatedPayment(String reference_code, String pay_hash, String test) throws NotFoundException {

		String payHash = t.getPayHash(reference_code);

		return payHash.equals(pay_hash) ? true : false;

	}

	/**
	 * Actualiza la tabla Transactions de la base de datos con la información que recibe desde el gateway de pagos
	 * @param response_code_pol
	 * @param state_pol
	 * @param response_message_pol
	 * @param payment_method_type
	 * @param transaction_date
	 * @param payment_method_name
	 * @return
	 * @throws ParseException
	 * @throws NotFoundException 
	 */
	private boolean updatedModel(String reference_code, String response_code_pol, String state_pol, String response_message_pol,
			String payment_method_type, String transaction_date, String payment_method_name) throws ParseException, NotFoundException {
		
		Transactions trans = t.getTransactionById(reference_code);

		trans.setResponseCodePol(Integer.parseInt(response_code_pol));
		trans.setStatePol(Integer.parseInt(state_pol));
		trans.setResponseMessageCol(response_message_pol);
		trans.setPaymentMethodType(Integer.parseInt(payment_method_name));

		SimpleDateFormat formatter = new SimpleDateFormat("dd.MMM.yyyy HH:mm:ss0");
		trans.setTransactionDate(formatter.parse(transaction_date));

		trans.setPaymentMethodName(payment_method_name);

		return true;
	}

	// Se actualiza el modelo
	// Si el estado es aprobado, entonces sigue lo que sucede si es aprobado

	// Api key: aBjMZC0Jw0nQef0ubg8K31o2av
	// Api login:CN5Ulr6Jda9d1nz
	// Llave p�blica:PK6313a9rsoGh33qS1B1WKZ3ET
	// Id Comercio:686066

	// $(document).ready(function(){
	// CargarCarrito();
	// });
	//
	//
	// function CargarCarrito()
	// {
	// $("#htotal").html("0");
	// $("#botonPayuContainer").html("");
	// $("#idPayuButtonContainer").html("");
	//
	// $.ajax({
	// dataType: "json",
	// method:"GET",
	// url: "/carrito/obtenercarrito"
	// })
	// .done(function( carrito ){
	// console.log(carrito);
	//
	// $("#tblCarrito").html("");
	// $("#ltotal").html("");
	//
	//
	// for(i=0;i<carrito.productos.length;i++)
	// {
	// var producto=carrito.productos[i];
	//
	//
	//
	// html_fila="<tr>";
	// html_fila+="<td><img height='40px' src='"+producto.ruta_imagen+"'></td>";
	// html_fila+="<td>"+producto.nombre+"</td>";
	// html_fila+="<td><input type='number' value='"+producto.cantidad+"'
	// onchange='ModificarProductoCarrito("+producto.id+",this)'></td>";
	// html_fila+="<td>"+producto.precio+"</td>";
	// html_fila+="<td>"+producto.subtotal+"</td>";
	// html_fila+="<td><a href='#'
	// onclick='EliminarProductoCarrito("+producto.id+")'>Eliminar</a></td>";
	// html_fila+="</tr>";
	//
	//
	//
	// $("#tblCarrito").append(html_fila);
	// }
	//
	//
	// $("#ltotal").append(carrito.total);
	//
	// $("#htotal").val(carrito.total);
	//
	//
	//
	// CrearBotonPayu();
	//
	//
	//
	// });
	// }
	// function ModificarProductoCarrito(idproducto,obj)
	// {
	// console.log(obj.value);
	//
	//
	//
	// $.ajax({
	// dataType: "json",
	// method:"GET",
	// url: "/carrito/modificarproducto/"+idproducto+"/"+obj.value
	// })
	// .done(function(){
	// console.log("Productoeliminafdo");
	// CargarCarrito();
	// }
	// )
	// .fail(function(msg){
	// console.log(msg);
	// })
	// ;
	//
	// }
	//
	//
	// function EliminarProductoCarrito(idproducto)
	// {
	// $.ajax({
	// dataType: "json",
	// method:"GET",
	// url: "/carrito/eliminarproducto/"+idproducto
	// })
	// .done(function(){
	// console.log("Productoeliminafdo");
	// CargarCarrito();
	// }
	// )
	// .fail(function(){
	// console.log("error de controller");
	// })
	// ;
	//
	// }

	// function CrearBotonPayu()
	// {
	//
	//
	// var montoPago=$("#htotal").val();
	//
	// $.ajax({
	// dataType: "json",
	// method:"GET",
	// url: "/pagos/obtenerinformacionpago/"+montoPago
	// })
	// .done(function( infopago ){
	//
	// var html_button="<form method='post'
	// action='https://sandbox.gateway.payulatam.com/ppp-web-gateway/'>\
	// <input name='merchantId' type='hidden' value='"+infopago.merchantId+"' >\
	// <input name='accountId' type='hidden' value='"+infopago.accountId+"' >\
	// <input name='description' type='hidden' value='"+infopago.description+"'
	// >\
	// <input name='referenceCode' type='hidden'
	// value='"+infopago.referenceCode+"' >\
	// <input name='amount' type='hidden' value='"+infopago.amount+"' >\
	// <input name='tax' type='hidden' value='"+infopago.tax+"' >\
	// <input name='taxReturnBase' type='hidden'
	// value='"+infopago.taxReturnBase+"' >\
	// <input name='currency' type='hidden' value='"+infopago.currency+"' >\
	// <input name='signature' type='hidden' value='"+infopago.signature+"' >\
	// <input name='test' type='hidden' value='"+infopago.test+"' >\
	// <input name='buyerEmail' type='hidden' value='"+infopago.buyerEmail+"' >\
	// <input name='responseUrl' type='hidden' value='"+infopago.responseUrl+"'
	// >\
	// <input name='confirmationUrl' type='hidden'
	// value='"+infopago.confirmationUrl+"' >\
	// <input name='Submit' type='submit' value='Pagar' >\
	// </form>";
	//
	// $("#idPayuButtonContainer").append(html_button);
	//
	// });
	//
	// }

}
