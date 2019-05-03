package ac.uk.shef.cc19grp10.payment;

import ac.uk.shef.cc19grp10.payment.data.AccountRepository;
import ac.uk.shef.cc19grp10.payment.data.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttribute;

/**
 * Configures some properties to be applied to all controllers.
 */
@ControllerAdvice
public class GlobalControllerAdvice {

    private final AccountRepository accountRepo;

    public GlobalControllerAdvice(AccountRepository accountRepo) {
        this.accountRepo = accountRepo;
    }

    /**
     * Sets up models for use globally within JSP.
     * @param model Model object which gets used by JSP.
     * @param user The current logged in user.
     */
    @ModelAttribute
    public void globalAttributes(
            Model model,
            @SessionAttribute(required = false) User user
    ) {
        if (user != null) {
            // Workaround for session issues, instead of using the account within the session.
            accountRepo.findAccountByOwner(user).ifPresent(user::setAccount);

            model.addAttribute("currentUser", user);
        }
    }

}
