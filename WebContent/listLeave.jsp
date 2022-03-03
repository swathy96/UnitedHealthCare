<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="domain.*"%>
<%@page import="bo.*"%>
	<%
		System.out.println("in list leave");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		session=request.getSession(false);  
		Integer sessionUserId = (Integer)session.getAttribute("userId");
	       
		User sessionUser = new UserBO().getUserById(sessionUserId);
		
		List<LeaveDetail> leaveList = (ArrayList<LeaveDetail>)request.getAttribute("leaveList");
		System.out.println(leaveList);
	%>
            <div class="apply-leave">
            <% if (!leaveList.isEmpty()) { %>
                <div class="flexbox-group">
                	<% for (LeaveDetail leaveDetail : leaveList) { %>
                    <div class="flex" id="leave<%= leaveDetail.getId() %>">
                        <div class="box-card">
                            <ul class="card-lists">
                                <li>
                                    <span class="label-text">Date</span>
                                    <label id="date<%= leaveDetail.getId() %>"><%= leaveDetail.getLeaveDate() %></label>
                                </li>
                                <li>
                                    <span class="label-text">Slot</span>
                                    <label id="slot<%= leaveDetail.getId() %>"><%= leaveDetail.getDoctorConsultationSlot().getConsultationSlot().getHours() %></label>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <%} %>
                </div>
                <%}else{ %>
                <h2 id="message">No leaves applied</h2>
                <%} %>
            </div>
