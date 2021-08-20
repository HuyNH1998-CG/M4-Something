package bigg.formatter;

import bigg.model.Role;
import bigg.services.IRoleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;

public class RoleFormatter implements Formatter<Role> {
    private IRoleServices roleServices;
    @Autowired
    public RoleFormatter(IRoleServices roleServices) {
        this.roleServices = roleServices;
    }

    @Override
    public Role parse(String text, Locale locale) throws ParseException {
        Optional<Role>role = roleServices.findById(Long.parseLong(text));
        return role.orElse(null);
    }

    @Override
    public String print(Role object, Locale locale) {
        return "[" + object.getId() + "," + object.getName() + "]";
    }
}
