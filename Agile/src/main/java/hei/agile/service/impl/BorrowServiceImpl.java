package hei.agile.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import hei.agile.dao.BookDAO;
import hei.agile.dao.BorrowDAO;
import hei.agile.dao.MemberDAO;
import hei.agile.entity.Book;
import hei.agile.entity.Borrow;
import hei.agile.entity.Member;
import hei.agile.service.BorrowService;

@Named
@Transactional
public class BorrowServiceImpl implements BorrowService {

	@Inject
	private BorrowDAO borrowDAO;	
	
	@Inject
	private BookDAO bookDAO;	
	
	@Inject
	private MemberDAO memberDAO;
	
public String createAutocomplete(){
		
		String script = null;
		List<Book> allBooks = bookDAO.findAll();
		List<Member> allMembers = memberDAO.findAll();
		//Get all books
		int i = 0;
		String availableBooks = "";
		for (Book book : allBooks) {
			if(i !=0){
				availableBooks = availableBooks +",";
			}
			availableBooks = availableBooks +"{label:\""+book.getTitleBook()+"\", value:"+book.getIdBook()+"}";
			i++;
		}
		
		int j = 0;
		String availableMembers = "";
		for (Member member : allMembers) {
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			if(j !=0){
				availableMembers = availableMembers +",";
			}
			availableMembers = availableMembers +"{label:\""+member.getNameMember()+"_"+member.getNicknameMember()+" "+df.format(member.getBirthDateMember())+"\", value:"+member.getIdMember()+"}";
			j++;
		}		
				script = "<script>\n";
				script += "$( document ).ready(function() {\n";
				script += "var availableBooks = ["+availableBooks+"];\n";
				script += "$(\"#titleBook\").autocomplete({source: availableBooks, focus: function(event,ui ) {$(\"#titleBook\").val(ui.item.label);return false;},select: function(event, ui) {$(\"#titleBook\").val(ui.item.label);$(\"#titleBook\").attr(\"data-value\",ui.item.value);return false;}});\n";
				script += "var availableMembers = ["+availableMembers+"];\n";
				script += "$(\"#membreBorrow\").autocomplete({source: availableMembers, focus: function(event,ui ) {$(\"#membreBorrow\").val(ui.item.label);return false;},select: function(event, ui) {$(\"#membreBorrow\").val(ui.item.label);$(\"#membreBorrow\").attr(\"data-value\",ui.item.value);return false;}});\n";
				script += "});\n";
				script += "</script>\n";
				getBorrowDate();
		return script;				
	}

public String getBorrowDate(){
	Date today = new Date();
	SimpleDateFormat FormattedDATE  = new SimpleDateFormat("yyyy-MM-dd");
	Calendar c = Calendar.getInstance(); 
	c.setTime(today); 
	c.add(Calendar.DATE, 30);
	return(FormattedDATE.format(c.getTime()));
}

public void saveBorrow(Borrow borrow) {
	borrowDAO.save(borrow);
}
}
