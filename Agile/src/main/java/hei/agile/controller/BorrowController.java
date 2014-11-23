package hei.agile.controller;

import hei.agile.entity.Book;
import hei.agile.entity.Borrow;
import hei.agile.entity.Member;
import hei.agile.service.BookService;
import hei.agile.service.BorrowService;
import hei.agile.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@Named
@RequestMapping("/")
public class BorrowController {
	
	private static final Logger logger = LoggerFactory.getLogger(BorrowController.class);
	
	@Inject
    private BorrowService borrowService;
	@Inject
    private BookService bookService;
	@Inject
    private MemberService memberService;
	
	@RequestMapping(value="/borrow",method = RequestMethod.GET)
    public String getForm(ModelMap model) {
		model.addAttribute("borrow", new Borrow());	
		model.addAttribute("books", borrowService.createAutocomplete());
		model.addAttribute("dateRest", borrowService.getBorrowDate());

		logger.debug("On fait de l'autocomplete YOLOWW");
		
        return "borrow/BorrowBookForm";
    }
	
	@RequestMapping(value="/borrow", method = RequestMethod.POST)
	public String addBorrow(HttpServletRequest request, ModelMap model) {
		long idBook = Long.parseLong(request.getParameter("idBook"));
		long idMember = Long.parseLong(request.getParameter("idMember"));

		List<String> errors = new ArrayList<>();

		idBook=10;
		Book book = bookService.findOne(idBook);

		if (book == null) {
			errors.add("Livre introuvable");
		} else if (borrowService.findBorrowByIdBook(idBook) != null){
			errors.add("Livre déjà emprunté");
		}

		Member member = memberService.findOne(idMember);
		if (member == null) {
			errors.add("Membre introuvable");
		}

		model.addAttribute("errors", errors);

		if (errors.isEmpty()) {
			Borrow borrow = new Borrow(book, member);
			borrowService.saveBorrow(borrow);
			logger.info("Ajout d'un emprunt : {} par: {} {}", (borrow.getBook()).getTitleBook(), (borrow.getMember()).getNameMember(), (borrow.getMember()).getNicknameMember());
			return "redirect:/borrow";
		} else {
			return "borrow/BorrowBookForm";
		}

    }
}
