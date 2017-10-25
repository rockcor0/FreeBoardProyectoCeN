package com.project.freeboard.payment;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.freeboard.entity.Auctions;
import com.project.freeboard.entity.Companies;
import com.project.freeboard.entity.Offers;
import com.project.freeboard.entity.Students;
import com.project.freeboard.entity.Transactions;
import com.project.freeboard.mensajes.ShareContacts;

//Modulo para validar y registrar pagos
public class DoPayment extends HttpServlet {

	public final static String STATE_POL_APPROVED = "4";
	public final static String RESPONSE_MESSAGE_POL_APPROVED = "APPROVED";
	public final static String RESPONSE_CODE_POL_APPROVED = "1";

	private Transactions t;
<<<<<<< HEAD
	private Offers o;
=======
>>>>>>> branch 'master' of https://github.com/rocco522/FreeBoardProyectoCeN.git
	private ShareContacts sc;

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String reference_code = req.getParameter("reference_sale"); // idTransaccion
		String description = req.getParameter("description");
		String amount = req.getParameter("amount"); // value en la respuesta
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

		t = new Transactions(reference_code);
		o = new Offers(id_oferta);

		// Valida si se trata de una operación real
		boolean validatedPayment = validatedPayment(pay_hash, test);

		boolean updated = false;

		boolean isTransactionApproved = false;
		
		boolean sendMessageToShareContacts = false;

		boolean sendMessageToShareContacts = false;

		try {
			if (validatedPayment) {
				updated = updatedModel(response_code_pol, state_pol, response_message_pol, payment_method_type,
						transaction_date, payment_method_name);
<<<<<<< HEAD
			}
=======
			} 
>>>>>>> branch 'master' of https://github.com/rocco522/FreeBoardProyectoCeN.git

			if (updated) {

				isTransactionApproved = isTransactionApproved(state_pol, response_code_pol, response_message_pol);
<<<<<<< HEAD
			}
=======

			} 
>>>>>>> branch 'master' of https://github.com/rocco522/FreeBoardProyectoCeN.git

			if (isTransactionApproved) {
				
				String emailStudent = consultStudentEmail(id_oferta);
				String emailBusiness = consultBusinessEmail(id_oferta);
				sendMessageToShareContacts = sendMessageToShareContacts(emailStudent, emailBusiness);

<<<<<<< HEAD
				String emailStudent = consultStudentEmail();
				String emailBusiness = consultBusinessEmail();
				sendMessageToShareContacts = sendMessageToShareContacts(emailStudent, emailBusiness);
			}
=======
			} 
>>>>>>> branch 'master' of https://github.com/rocco522/FreeBoardProyectoCeN.git

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String consultBusinessEmail() {
		Auctions auction = o.getAuctionsIdauctions();
		Companies company = auction.getCompaniesId();
		String companyEmail = company.getEmail();

		return companyEmail;
	}

	private String consultStudentEmail() {
		Students student = o.getStudentsId();
		String studentEmail = student.getEmail();

		return studentEmail;
	}

	private boolean sendMessageToShareContacts(String emailStudent, String emailBusiness) {
		sc = new ShareContacts();
		return sc.sendMessage(emailStudent, emailBusiness) ? true : false;
	}

	private String consultBusinessEmail(String id_oferta) {
		// TODO Auto-generated method stub
		return null;
	}

	private String consultStudentEmail(String id_oferta) {
		// TODO Auto-generated method stub
		return null;
	}

	private boolean sendMessageToShareContacts(String emailStudent, String emailBusiness) {	
		sc = new ShareContacts();
		return sc.sendMessage(emailStudent, emailBusiness) ? true : false;
	}

	private boolean isTransactionApproved(String state_pol, String response_code_pol, String response_message_pol) {

		if (state_pol.equals(STATE_POL_APPROVED) && response_code_pol.equals(RESPONSE_CODE_POL_APPROVED)
				&& response_message_pol.equals(RESPONSE_MESSAGE_POL_APPROVED))
			return true;

		else
			return false;

	}

	private String checkTransactionStatus() {
		return null;
	}

	private boolean validatedPayment(String pay_hash, String test) {

		String payHash = t.getPayHash();

		return payHash.equals(pay_hash) ? true : false;

	}

	private boolean updatedModel(String response_code_pol, String state_pol, String response_message_pol,
			String payment_method_type, String transaction_date, String payment_method_name) throws ParseException {

		t.setResponseCodePol(Integer.parseInt(response_code_pol));
		t.setStatePol(Integer.parseInt(state_pol));
		t.setResponseMessageCol(response_message_pol);
		t.setPaymentMethodType(Integer.parseInt(payment_method_name));

		SimpleDateFormat formatter = new SimpleDateFormat("dd.MMM.yyyy HH:mm:ss0");
		t.setTransactionDate(formatter.parse(transaction_date));

		t.setPaymentMethodName(payment_method_name);

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
