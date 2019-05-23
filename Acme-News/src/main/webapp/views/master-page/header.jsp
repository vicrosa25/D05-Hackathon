
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<div>
	<img src="images/logo.png" alt="Acme-News Co., Inc." />
</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<security:authorize access="hasRole('ADMIN')">
			<li><a class="fNiv"><spring:message	code="master.page.administrator" /></a>
				<ul>
					<li class="arrow"></li>
					
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('USUARIO')">
			<li><a class="fNiv"><spring:message	code="master.page.profile" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="usuario/display.do"><spring:message code="master.page.diplay" /></a></li>
					<li><a href="usuario/edit.do"><spring:message code="master.page.edit" /></a></li>
					<li><a href="usuario/listSorteosProximos.do"><spring:message code="usuario.listSorteosProximos" /></a></li>
					<li><a href="usuario/listTusSorteos.do"><spring:message code="usuario.listTusSorteos" /></a></li>
					<li><a href="usuario/cambiarEstatus.do"><spring:message code="usuario.cambiarEstatus" /></a></li>			
				</ul>
			</li>
			<li><a class="fNiv"><spring:message	code="master.page.buscador" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="buscador/usuario/edit.do"><spring:message code="master.page.buscador.edit" /></a></li>
					<li><a href="buscador/usuario/result.do"><spring:message code="master.page.buscador.result" /></a></li>
				</ul>
			</li>
			<li><a class="fNiv"><spring:message	code="master.page.social" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="usuario/listUsuariosSiguiendo.do"><spring:message code="usuario.siguiendo" /></a></li>
					<li><a href="usuario/listInformacionDeQuienSigues.do"><spring:message code="usuario.informacionDeQuienSigues" /></a></li>
					<li><a href="noticia/usuario/recibidas.do"><spring:message code="usuario.recibidas" /></a></li>
				</ul>
			</li>
		</security:authorize>
		
		
		<security:authorize access="hasRole('ADMIN')">
				<li><a href="administrador/changeBanner.do"><spring:message code="master.page.administrador.banner" /></a></li>
				<li><a href="premio/admin/listAdmin.do"><spring:message code="master.page.administrator.premios" /></a></li>
				<li><a href="sorteo/admin/listAdmin.do"><spring:message code="master.page.administrator.sorteos" /></a></li>
				<li><a href="administrator/dashboard.do"><spring:message code="master.page.administrator.dashboard" /></a></li>
				<li><a href="tasa/administrator/changeTasa.do"><spring:message code="master.page.administrator.changeFee" /></a></li>
		</security:authorize>
		
		<security:authorize access="hasRole('PERIODISTA')">
			<li><a class="fNiv"><spring:message	code="master.page.profile" /></a>
				<ul>
					<li class="arrow"></li>
<%-- 				<li><a href="usuario/display.do"><spring:message code="master.page.diplay" /></a></li> --%>
					<li><a href="periodista/edit.do"><spring:message code="master.page.edit" /></a></li>
					
				</ul>
			</li>
			<li><a class="fNiv"><spring:message	code="master.page.periodista" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="noticia/misNoticias.do"><spring:message code="periodista.misNoticias" /></a></li>
					<li><a href="noticia/crear.do"><spring:message code="periodista.noticia.create" /></a></li>
					<li><a href="evento/misEventos.do"><spring:message code="periodista.misEventos" /></a></li>
					<li><a href="evento/crear.do"><spring:message code="periodista.evento.create" /></a></li>
					<li><a href="periodista/listPeriodista.do"><spring:message code="periodista.list.others" /></a></li>
					
				</ul>
				<li><a href="periodista/retirarDinero.do"><spring:message code="master.page.administrator.retirarDinero" /></a></li>
			</li>
			<li><a class="fNiv"><spring:message	code="master.page.agencia" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="agencia/listNotFullAgencia.do"><spring:message code="master.page.agencia.list" /></a></li>
					<li><a href="agencia/left.do"><spring:message code="master.page.left.agencia" /></a></li>
					
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('MANAGER')">
			<li><a class="fNiv"><spring:message	code="master.page.agencia" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="agencia/listAgencia.do"><spring:message code="master.page.agencia.listAll" /></a></li>
					<li><a href="agencia/createAgencia.do"><spring:message code="master.page.agencia.create" /></a></li>
					
				</ul>
			</li>
		</security:authorize>
			
		<security:authorize access="hasRole('MODERADOR')">
			<li><a href="noticia/listBanear.do"><spring:message code="moderador.listBanear" /></a></li>
			<li><a href="usuario/listUsuariosToBan.do"><spring:message code="master.page.administrator.usuariosToBan" /></a></li>
			<li><a href="noticia/listPendientes.do"><spring:message code="moderador.listPendientes" /></a></li>
			<li><a href="moderador/retirarDinero.do"><spring:message code="master.page.administrator.retirarDinero" /></a></li>
		</security:authorize>
		
		<security:authorize access="isAnonymous()">
			<li><a class="fNiv"><spring:message	code="master.page.registration" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="registration/usuario.do"><spring:message code="registration.usuario" /></a></li>
					<li><a href="registration/periodista.do"><spring:message code="registration.periodista" /></a></li>
					<li><a href="registration/moderador.do"><spring:message code="registration.moderador" /></a></li>
				</ul>
			</li>
		</security:authorize>
		<security:authorize access="permitAll()">
			<li><a class="fNiv"><spring:message	code="master.page.noticias" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="noticia/buscar.do"><spring:message code="noticia.buscar" /></a></li>
					<li><a href="noticia/listaDeportes.do"><spring:message code="noticia.deportes" /></a></li>
					<li><a href="noticia/listaEconomia.do"><spring:message code="noticia.economia" /></a></li>
					<li><a href="noticia/listaMotor.do"><spring:message code="noticia.motor" /></a></li>
					<li><a href="noticia/listaCultura.do"><spring:message code="noticia.cultura" /></a></li>
					<li><a href="noticia/listaOcio.do"><spring:message code="noticia.ocio" /></a></li>
					<li><a href="noticia/listaEspana.do"><spring:message code="noticia.españa" /></a></li>
					<li><a href="noticia/listaInternacional.do"><spring:message code="noticia.internacional" /></a></li>
					<li><a href="noticia/listaJuegos.do"><spring:message code="noticia.juegos" /></a></li>
					<li><a href="noticia/listaOtros.do"><spring:message code="noticia.otros" /></a></li>
				</ul>
			</li>
			<li><a class="fNiv" href="evento/allEventos.do"><spring:message code="master.page.eventos" /></a></li>
		</security:authorize>		
		
		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a></li>
		</security:authorize>

		<security:authorize access="isAuthenticated()">
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /></a></li>
		</security:authorize>
	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

