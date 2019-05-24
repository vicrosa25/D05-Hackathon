
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>


<div>
	<a href="#"><img src="${logo}" alt="${title}" width="800" height="240" /></a>
</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		
		
		
		<!-- ADMIN -->
		<security:authorize access="hasRole('ADMIN')">
			<li><a class="fNiv"><spring:message	code="master.page.administrator" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="manager/admin/list.do"><spring:message code="master.page.manager.list" /></a></li>
					<li><a href="administrator/dashboard.do"><spring:message code="master.page.administrator.dashboard" /></a></li>
					<li><a href="administrador/changeBanner.do"><spring:message code="master.page.administrador.banner" /></a></li>
					<li><a href="premio/admin/listAdmin.do"><spring:message code="master.page.administrator.premios" /></a></li>
					<li><a href="sorteo/admin/listAdmin.do"><spring:message code="master.page.administrator.sorteos" /></a></li>
					<li><a href="tasa/administrator/changeTasa.do"><spring:message code="master.page.administrator.changeFee" /></a></li>
				</ul>
			</li>
			<li><a class="fNiv"><spring:message code="master.page.configurations" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="administrator/config/cache/edit.do"><spring:message code="master.page.cache" /></a></li>
					<li><a href="administrator/config/aliveConfig/edit.do"><spring:message code="master.page.settings" /></a></li>
					<li><a href="administrator/config/spam/list.do"><spring:message code="master.page.spam.words" /></a></li>
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
					<li><a href="usuario/listPeriodistasSiguiendo.do"><spring:message code="periodista.siguiendo" /></a></li>
					<li><a href="usuario/listInformacionDeQuienSigues.do"><spring:message code="usuario.informacionDeQuienSigues" /></a></li>
					<li><a href="noticia/usuario/recibidas.do"><spring:message code="usuario.recibidas" /></a></li>
				</ul>
			</li>
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
			<li><a class="fNiv"><spring:message	code="master.page.profile" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="manager/update.do"><spring:message code="master.page.manager.update" /></a></li>			
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
					<li><a href="noticia/lista.do"><spring:message code="noticia.todas" /></a></li>
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

