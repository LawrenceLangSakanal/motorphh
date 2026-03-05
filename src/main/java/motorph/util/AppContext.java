package motorph.util;

import motorph.model.Employee;

/**
 * Application-wide session context that holds the currently logged-in employee.
 * This is a lightweight companion to the constructor-injection approach used
 * throughout the app; components can use either mechanism.
 */
public final class AppContext {

    private static volatile AppContext instance;

    private volatile Employee currentEmployee;

    private AppContext() {}

    public static AppContext getInstance() {
        if (instance == null) {
            synchronized (AppContext.class) {
                if (instance == null) {
                    instance = new AppContext();
                }
            }
        }
        return instance;
    }

    /** Store the employee who just logged in. */
    public void setCurrentEmployee(Employee employee) {
        this.currentEmployee = employee;
    }

    /** Returns the currently logged-in employee, or {@code null} if no session is active. */
    public Employee getCurrentEmployee() {
        return currentEmployee;
    }

    /** Clears the session (call on logout). */
    public void clearSession() {
        this.currentEmployee = null;
    }

    /** Convenience check for an active session. */
    public boolean isLoggedIn() {
        return currentEmployee != null;
    }
}
