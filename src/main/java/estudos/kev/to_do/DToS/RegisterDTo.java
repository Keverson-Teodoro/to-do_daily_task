package estudos.kev.to_do.DToS;


import estudos.kev.to_do.model.enums.UserRole;

public record RegisterDTo(String login, String password, UserRole role) {
}
