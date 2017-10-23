package com.project.freeboard.payment;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.freeboard.entity.Transactions;


public class DoPayment extends HttpServlet {
	
	private Transactions t;

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

		//Valida si se trata de una operación real
		boolean validatedPayment = validatedPayment(pay_hash, reference_code, test);

		boolean updated = false;

		if (validatedPayment) {
			updated = updatedModel(reference_code, id_oferta, response_code_pol, state_pol, response_message_pol,
					payment_method_type, transaction_date, payment_method_name);
		} else {
			//Lanzar exception
		}

		if (updated) {

		} else {
			//lanzar excepción
		}
	}

	private boolean validatedPayment(String pay_hash, String reference_code, String test) {
		boolean validated = false;
		
		return validated;
	}

	private boolean updatedModel(String reference_code, String id_oferta, String response_code_pol, String state_pol,
			String response_message_pol, String payment_method_type, String transaction_date,
			String payment_method_name) {
		// TODO Auto-generated method stub

		return false;
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