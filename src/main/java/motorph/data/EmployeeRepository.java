package motorph.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import motorph.model.Employee;
import motorph.model.ProbationaryEmployee;
import motorph.model.RegularEmployee;

/**
 * Repository that stores employees using a hardcoded dataset (IDs 10001-10034).
 * No external Excel file required.
 */
public class EmployeeRepository {

    private final List<Employee> employees = new ArrayList<>();

    public EmployeeRepository() {
        loadSampleData();
    }

    public List<Employee> getAll() {
        return employees;
    }

    public Optional<Employee> findById(int employeeId) {
        return employees.stream()
                .filter(e -> e.getEmployeeId() == employeeId)
                .findFirst();
    }

    public List<Employee> search(String query) {
        if (query == null || query.trim().isEmpty()) return employees;
        String q = query.trim().toLowerCase();
        List<Employee> results = new ArrayList<>();
        for (Employee e : employees) {
            if (String.valueOf(e.getEmployeeId()).contains(q)
                    || e.getFullName().toLowerCase().contains(q)
                    || e.getLastName().toLowerCase().contains(q)
                    || e.getFirstName().toLowerCase().contains(q)) {
                results.add(e);
            }
        }
        return results;
    }

    public Optional<Employee> authenticate(int employeeId, String password) {
        return employees.stream()
                .filter(e -> e.getEmployeeId() == employeeId
                        && e.getPassword() != null
                        && e.getPassword().equals(password))
                .findFirst();
    }

    // -------------------------------------------------------
    // Hardcoded MotorPH Employee Dataset (10001-10034)
    // Default password for all: "admin"
    // -------------------------------------------------------
    private void loadSampleData() {

        // 10001
        Employee e10001 = new RegularEmployee(
                10001, "admin", "Garcia", "Manuel III",
                "10/11/1983",
                "Valero Carpark Building Valero Street 1227, Makati City",
                "966-860-270",
                "44-4506057-3", "820126853951", "442-605-657-000", "691295330870",
                "Chief Executive Officer", "N/A",
                90000, 1500, 2000, 1000, 45000, 535.71
        );
        e10001.setSssDeduction(1125.00);
        e10001.setPhilhealthDeduction(900.00);
        e10001.setPagibigDeduction(100.00);
        e10001.setTaxDeduction(17195.40);
        employees.add(e10001);

        // 10002
        Employee e10002 = new RegularEmployee(
                10002, "admin", "Lim", "Antonio",
                "06/19/1988",
                "San Antonio De Padua 2, Block 1 Lot 8 and 2, Dasmarinas, Cavite",
                "171-867-411",
                "52-2061274-9", "331735646338", "683-102-776-000", "663904995411",
                "Chief Operating Officer", "Garcia, Manuel III",
                60000, 1500, 2000, 1000, 30000, 357.14
        );
        e10002.setSssDeduction(1125.00);
        e10002.setPhilhealthDeduction(900.00);
        e10002.setPagibigDeduction(100.00);
        e10002.setTaxDeduction(8635.50);
        employees.add(e10002);

        // 10003
        Employee e10003 = new RegularEmployee(
                10003, "admin", "Aquino", "Bianca Sofia",
                "08/04/1989",
                "Rm. 402 4/F Jiao Building Timog Avenue Cor. Quezon Avenue 1100, Quezon City",
                "966-889-370",
                "30-8870406-2", "177451189665", "971-711-280-000", "171519773969",
                "Chief Finance Officer", "Garcia, Manuel III",
                60000, 1500, 2000, 1000, 30000, 357.14
        );
        e10003.setSssDeduction(1125.00);
        e10003.setPhilhealthDeduction(900.00);
        e10003.setPagibigDeduction(100.00);
        e10003.setTaxDeduction(8635.50);
        employees.add(e10003);

        // 10004
        Employee e10004 = new RegularEmployee(
                10004, "admin", "Reyes", "Isabella",
                "06/16/1994",
                "460 Solanda Street Intramuros 1000, Manila",
                "786-868-477",
                "40-2511815-0", "341911411254", "876-809-437-000", "416946776041",
                "Chief Marketing Officer", "Garcia, Manuel III",
                60000, 1500, 2000, 1000, 30000, 357.14
        );
        e10004.setSssDeduction(1125.00);
        e10004.setPhilhealthDeduction(900.00);
        e10004.setPagibigDeduction(100.00);
        e10004.setTaxDeduction(8635.50);
        employees.add(e10004);

        // 10005
        Employee e10005 = new RegularEmployee(
                10005, "admin", "Hernandez", "Eduard",
                "09/23/1989",
                "National Highway, Gingoog, Misamis Occidental",
                "088-861-012",
                "50-5577638-1", "957436191812", "031-702-374-000", "952347222457",
                "IT Operations and Systems", "Lim, Antonio",
                52670, 1500, 1000, 1000, 26335, 313.51
        );
        e10005.setSssDeduction(1125.00);
        e10005.setPhilhealthDeduction(790.05);
        e10005.setPagibigDeduction(100.00);
        e10005.setTaxDeduction(6830.49);
        employees.add(e10005);

        // 10006
        Employee e10006 = new RegularEmployee(
                10006, "admin", "Villanueva", "Andrea Mae",
                "02/14/1988",
                "17/85 Stracke Via Suite 042, Poblacion, Las Piñas 4783 Dinagat Islands",
                "918-621-603",
                "49-1632020-8", "382189453145", "317-674-022-000", "441093369646",
                "HR Manager", "Lim, Antonio",
                52670, 1500, 1000, 1000, 26335, 313.51
        );
        e10006.setSssDeduction(1125.00);
        e10006.setPhilhealthDeduction(790.05);
        e10006.setPagibigDeduction(100.00);
        e10006.setTaxDeduction(6830.49);
        employees.add(e10006);

        // 10007
        Employee e10007 = new RegularEmployee(
                10007, "admin", "San Jose", "Brad",
                "03/15/1996",
                "99 Strosin Hills, Poblacion, Bislig 5340 Tawi-Tawi",
                "797-009-261",
                "40-2400714-1", "239192926939", "672-474-690-000", "210850209964",
                "HR Team Leader", "Villanueva, Andrea Mae",
                42975, 1500, 800, 800, 21488, 255.80
        );
        e10007.setSssDeduction(1125.00);
        e10007.setPhilhealthDeduction(644.63);
        e10007.setPagibigDeduction(100.00);
        e10007.setTaxDeduction(4443.09);
        employees.add(e10007);

        // 10008
        Employee e10008 = new RegularEmployee(
                10008, "admin", "Romualdez", "Alice",
                "05/14/1992",
                "12A/33 Upton Isle Apt. 420, Roxas City 1814 Surigao del Norte",
                "983-606-799",
                "55-4476527-2", "545652640232", "888-572-294-000", "211385556888",
                "HR Rank and File", "San Jose, Brad",
                22500, 1500, 500, 500, 11250, 133.93
        );
        e10008.setSssDeduction(1012.50);
        e10008.setPhilhealthDeduction(337.50);
        e10008.setPagibigDeduction(100.00);
        e10008.setTaxDeduction(43.40);
        employees.add(e10008);

        // 10009
        Employee e10009 = new RegularEmployee(
                10009, "admin", "Atienza", "Rosie",
                "09/24/1948",
                "90A Dibbert Terrace Apt. 190, San Lorenzo 6056 Davao del Norte",
                "266-036-427",
                "41-0644692-3", "708988234853", "604-997-793-000", "260107732354",
                "HR Rank and File", "San Jose, Brad",
                22500, 1500, 500, 500, 11250, 133.93
        );
        e10009.setSssDeduction(1012.50);
        e10009.setPhilhealthDeduction(337.50);
        e10009.setPagibigDeduction(100.00);
        e10009.setTaxDeduction(43.40);
        employees.add(e10009);

        // 10010
        Employee e10010 = new RegularEmployee(
                10010, "admin", "Alvaro", "Roderick",
                "03/30/1988",
                "#284 T. Morato corner, Scout Rallos Street, Quezon City",
                "053-381-386",
                "64-7605054-4", "578114853194", "525-420-419-000", "799254095212",
                "Accounting Head", "Aquino, Bianca Sofia",
                52670, 1500, 1000, 1000, 26335, 313.51
        );
        e10010.setSssDeduction(1125.00);
        e10010.setPhilhealthDeduction(790.05);
        e10010.setPagibigDeduction(100.00);
        e10010.setTaxDeduction(6830.49);
        employees.add(e10010);

        // 10011
        Employee e10011 = new RegularEmployee(
                10011, "admin", "Salcedo", "Anthony",
                "09/14/1993",
                "93/54 Shanahan Alley Apt. 183, Santo Tomas 1572 Masbate",
                "070-766-300",
                "26-9647608-3", "126445315651", "210-805-911-000", "218002473454",
                "Payroll Manager", "Alvaro, Roderick",
                50825, 1500, 1000, 1000, 25413, 302.53
        );
        e10011.setSssDeduction(1125.00);
        e10011.setPhilhealthDeduction(762.38);
        e10011.setPagibigDeduction(100.00);
        e10011.setTaxDeduction(6376.16);
        employees.add(e10011);

        // 10012
        Employee e10012 = new RegularEmployee(
                10012, "admin", "Lopez", "Josie",
                "01/14/1987",
                "49 Springs Apt. 266, Poblacion, Taguig 3200 Occidental Mindoro",
                "478-355-427",
                "44-8563448-3", "431709011012", "218-489-737-000", "113071293354",
                "Payroll Team Leader", "Salcedo, Anthony",
                38475, 1500, 800, 800, 19238, 229.02
        );
        e10012.setSssDeduction(1125.00);
        e10012.setPhilhealthDeduction(577.13);
        e10012.setPagibigDeduction(100.00);
        e10012.setTaxDeduction(3334.97);
        employees.add(e10012);

        // 10013
        Employee e10013 = new RegularEmployee(
                10013, "admin", "Farala", "Martha",
                "01/11/1942",
                "42/25 Sawayn Stream, Ubay 1208 Zamboanga del Norte",
                "329-034-366",
                "45-5656375-0", "233693897247", "210-835-851-000", "631130283546",
                "Payroll Rank and File", "Salcedo, Anthony",
                24000, 1500, 500, 500, 12000, 142.86
        );
        e10013.setSssDeduction(1080.00);
        e10013.setPhilhealthDeduction(360.00);
        e10013.setPagibigDeduction(100.00);
        e10013.setTaxDeduction(325.40);
        employees.add(e10013);

        // 10014
        Employee e10014 = new RegularEmployee(
                10014, "admin", "Martinez", "Leila",
                "07/11/1970",
                "37/46 Kulas Roads, Maragondon 0962 Quirino",
                "877-110-749",
                "27-2090996-4", "515741057496", "275-792-513-000", "101205445886",
                "Payroll Rank and File", "Salcedo, Anthony",
                24000, 1500, 500, 500, 12000, 142.86
        );
        e10014.setSssDeduction(1080.00);
        e10014.setPhilhealthDeduction(360.00);
        e10014.setPagibigDeduction(100.00);
        e10014.setTaxDeduction(325.40);
        employees.add(e10014);

        // 10015
        Employee e10015 = new RegularEmployee(
                10015, "admin", "Romualdez", "Fredwin",
                "03/10/1985",
                "22A Gochan Lane, Cebu City 6000",
                "023-079-009",
                "26-8768374-1", "308366830853", "598-532-115-000", "223477348581",
                "Account Team Leader", "Alvaro, Roderick",
                38475, 1500, 800, 800, 19238, 229.02
        );
        e10015.setSssDeduction(1125.00);
        e10015.setPhilhealthDeduction(577.13);
        e10015.setPagibigDeduction(100.00);
        e10015.setTaxDeduction(3334.97);
        employees.add(e10015);

        // 10016
        Employee e10016 = new RegularEmployee(
                10016, "admin", "Mata", "Christian",
                "10/21/1987",
                "113 Yost Greens, Valenzuela 1440 Bukidnon",
                "046-519-772",
                "49-2959312-6", "824187961962", "103-100-522-000", "212141893454",
                "Account Rank and File", "Romualdez, Fredwin",
                24000, 1500, 500, 500, 12000, 142.86
        );
        e10016.setSssDeduction(1080.00);
        e10016.setPhilhealthDeduction(360.00);
        e10016.setPagibigDeduction(100.00);
        e10016.setTaxDeduction(325.40);
        employees.add(e10016);

        // 10017
        Employee e10017 = new RegularEmployee(
                10017, "admin", "De Leon", "Selena",
                "02/20/1975",
                "56 Ipil Street, Malabon 1470",
                "975-901-573",
                "40-2400714-1", "587172565123", "245-871-600-000", "515841125478",
                "Account Rank and File", "Romualdez, Fredwin",
                24000, 1500, 500, 500, 12000, 142.86
        );
        e10017.setSssDeduction(1080.00);
        e10017.setPhilhealthDeduction(360.00);
        e10017.setPagibigDeduction(100.00);
        e10017.setTaxDeduction(325.40);
        employees.add(e10017);

        // 10018
        Employee e10018 = new RegularEmployee(
                10018, "admin", "San Jose", "Allison",
                "06/24/1986",
                "14B Evangelista Street, Pasig City 1600",
                "179-075-129",
                "45-3251383-0", "745148459521", "395-032-717-000", "114627865289",
                "Account Rank and File", "Romualdez, Fredwin",
                24000, 1500, 500, 500, 12000, 142.86
        );
        e10018.setSssDeduction(1080.00);
        e10018.setPhilhealthDeduction(360.00);
        e10018.setPagibigDeduction(100.00);
        e10018.setTaxDeduction(325.40);
        employees.add(e10018);

        // 10019
        Employee e10019 = new ProbationaryEmployee(
                10019, "admin", "Rosario", "Cydney",
                "10/06/1996",
                "Villa Fidel Subdivision, Davao City 8000",
                "868-819-912",
                "49-1632020-8", "579611414162", "317-674-022-000", "114627865123",
                "Account Rank and File", "Romualdez, Fredwin",
                24000, 1500, 500, 500, 12000, 142.86
        );
        e10019.setSssDeduction(1080.00);
        e10019.setPhilhealthDeduction(360.00);
        e10019.setPagibigDeduction(100.00);
        e10019.setTaxDeduction(325.40);
        employees.add(e10019);

        // 10020
        Employee e10020 = new RegularEmployee(
                10020, "admin", "Bautista", "Mark",
                "02/12/1991",
                "Lot 42 Block 5, Phase 2, Camella Homes, Taguig",
                "683-725-348",
                "52-2061274-9", "746612420184", "525-420-419-000", "326002454458",
                "Supply Chain and Logistics", "Reyes, Isabella",
                52670, 1500, 1000, 1000, 26335, 313.51
        );
        e10020.setSssDeduction(1125.00);
        e10020.setPhilhealthDeduction(790.05);
        e10020.setPagibigDeduction(100.00);
        e10020.setTaxDeduction(6830.49);
        employees.add(e10020);

        // 10021
        Employee e10021 = new RegularEmployee(
                10021, "admin", "Lazaro", "Darlene",
                "11/25/1985",
                "25 Banawe Street, Quezon City 1114",
                "673-027-518",
                "26-9647608-3", "124245978951", "210-805-911-000", "218002478856",
                "Supply Chain Team Leader", "Bautista, Mark",
                42975, 1500, 800, 800, 21488, 255.80
        );
        e10021.setSssDeduction(1125.00);
        e10021.setPhilhealthDeduction(644.63);
        e10021.setPagibigDeduction(100.00);
        e10021.setTaxDeduction(4443.09);
        employees.add(e10021);

        // 10022
        Employee e10022 = new RegularEmployee(
                10022, "admin", "Delos Santos", "Kolby",
                "02/26/1980",
                "Blk 8 Lot 1 Mountainview Subdivision, San Jose del Monte, Bulacan",
                "354-036-428",
                "55-4476527-2", "434517852178", "888-572-294-000", "210850782564",
                "Supply Chain Rank and File", "Lazaro, Darlene",
                22500, 1500, 500, 500, 11250, 133.93
        );
        e10022.setSssDeduction(1012.50);
        e10022.setPhilhealthDeduction(337.50);
        e10022.setPagibigDeduction(100.00);
        e10022.setTaxDeduction(43.40);
        employees.add(e10022);

        // 10023
        Employee e10023 = new RegularEmployee(
                10023, "admin", "Santos", "Vella",
                "12/31/1998",
                "Purok 3, Barangay Dao, General Santos City 9500",
                "886-505-381",
                "41-0644692-3", "218489416451", "604-997-793-000", "260107785421",
                "Supply Chain Rank and File", "Lazaro, Darlene",
                22500, 1500, 500, 500, 11250, 133.93
        );
        e10023.setSssDeduction(1012.50);
        e10023.setPhilhealthDeduction(337.50);
        e10023.setPagibigDeduction(100.00);
        e10023.setTaxDeduction(43.40);
        employees.add(e10023);

        // 10024
        Employee e10024 = new ProbationaryEmployee(
                10024, "admin", "Del Rosario", "Tomas",
                "07/28/1978",
                "Block 17 Lot 2, Villa Verde Subdivision, Imus, Cavite",
                "639-432-687",
                "64-7605054-4", "798456231987", "525-420-615-000", "799254125478",
                "Supply Chain Rank and File", "Lazaro, Darlene",
                22500, 1500, 500, 500, 11250, 133.93
        );
        e10024.setSssDeduction(1012.50);
        e10024.setPhilhealthDeduction(337.50);
        e10024.setPagibigDeduction(100.00);
        e10024.setTaxDeduction(43.40);
        employees.add(e10024);

        // 10025
        Employee e10025 = new RegularEmployee(
                10025, "admin", "Tolentino", "Jacklyn",
                "05/19/1984",
                "12 Santan Street, BF Homes, Paranaque 1720",
                "961-812-044",
                "50-5577638-1", "912345678912", "031-702-519-000", "952347289451",
                "Customer Service Head", "Reyes, Isabella",
                52670, 1500, 1000, 1000, 26335, 313.51
        );
        e10025.setSssDeduction(1125.00);
        e10025.setPhilhealthDeduction(790.05);
        e10025.setPagibigDeduction(100.00);
        e10025.setTaxDeduction(6830.49);
        employees.add(e10025);

        // 10026
        Employee e10026 = new RegularEmployee(
                10026, "admin", "Gutierrez", "Percival",
                "12/18/1990",
                "1 Cadiz Avenue Extension, Brgy. San Isidro, Antipolo 1870",
                "812-462-227",
                "49-1632020-8", "687456213547", "317-674-145-000", "441093455689",
                "Customer Service Team Leader", "Tolentino, Jacklyn",
                38475, 1500, 800, 800, 19238, 229.02
        );
        e10026.setSssDeduction(1125.00);
        e10026.setPhilhealthDeduction(577.13);
        e10026.setPagibigDeduction(100.00);
        e10026.setTaxDeduction(3334.97);
        employees.add(e10026);

        // 10027
        Employee e10027 = new RegularEmployee(
                10027, "admin", "Manalaysay", "Gareth",
                "08/28/1979",
                "5 Kamuning Road, Quezon City 1103",
                "724-675-748",
                "40-2400714-1", "456123789456", "672-474-852-000", "210850356412",
                "Customer Service Rank and File", "Gutierrez, Percival",
                24000, 1500, 500, 500, 12000, 142.86
        );
        e10027.setSssDeduction(1080.00);
        e10027.setPhilhealthDeduction(360.00);
        e10027.setPagibigDeduction(100.00);
        e10027.setTaxDeduction(325.40);
        employees.add(e10027);

        // 10028
        Employee e10028 = new RegularEmployee(
                10028, "admin", "Villegas", "Lizeth",
                "12/12/1986",
                "45B Cordillera Street, Sta. Mesa Heights, Quezon City 1114",
                "551-396-585",
                "45-3251383-0", "345612789145", "395-032-891-000", "114627912354",
                "Customer Service Rank and File", "Gutierrez, Percival",
                24000, 1500, 500, 500, 12000, 142.86
        );
        e10028.setSssDeduction(1080.00);
        e10028.setPhilhealthDeduction(360.00);
        e10028.setPagibigDeduction(100.00);
        e10028.setTaxDeduction(325.40);
        employees.add(e10028);

        // 10029
        Employee e10029 = new ProbationaryEmployee(
                10029, "admin", "Ramos", "Carol",
                "04/15/1992",
                "16 Sampaguita Street, Capitol Subdivision, Pasig 1600",
                "473-589-221",
                "52-2061274-9", "789123456789", "683-102-912-000", "663904112365",
                "Customer Service Rank and File", "Gutierrez, Percival",
                24000, 1500, 500, 500, 12000, 142.86
        );
        e10029.setSssDeduction(1080.00);
        e10029.setPhilhealthDeduction(360.00);
        e10029.setPagibigDeduction(100.00);
        e10029.setTaxDeduction(325.40);
        employees.add(e10029);

        // 10030
        Employee e10030 = new RegularEmployee(
                10030, "admin", "Maceda", "Emelia",
                "11/16/1981",
                "Upper Bacayan, Cebu City 6000",
                "612-417-808",
                "26-9647608-3", "912456187234", "210-805-478-000", "218002534875",
                "Sales and Marketing Head", "Reyes, Isabella",
                52670, 1500, 1000, 1000, 26335, 313.51
        );
        e10030.setSssDeduction(1125.00);
        e10030.setPhilhealthDeduction(790.05);
        e10030.setPagibigDeduction(100.00);
        e10030.setTaxDeduction(6830.49);
        employees.add(e10030);

        // 10031
        Employee e10031 = new RegularEmployee(
                10031, "admin", "Aguilar", "Lora",
                "01/22/1989",
                "28 Westpoint Street, Cubao, Quezon City 1109",
                "747-611-772",
                "44-8563448-3", "478123456951", "218-489-895-000", "113071347852",
                "Sales Team Leader", "Maceda, Emelia",
                38475, 1500, 800, 800, 19238, 229.02
        );
        e10031.setSssDeduction(1125.00);
        e10031.setPhilhealthDeduction(577.13);
        e10031.setPagibigDeduction(100.00);
        e10031.setTaxDeduction(3334.97);
        employees.add(e10031);

        // 10032
        Employee e10032 = new RegularEmployee(
                10032, "admin", "Castro", "Jo",
                "03/14/1992",
                "37 Legaspi Street, Brgy. San Antonio, Makati City 1203",
                "871-127-953",
                "45-5656375-0", "234567891234", "210-835-412-000", "631130445218",
                "Sales Rank and File", "Aguilar, Lora",
                24000, 1500, 500, 500, 12000, 142.86
        );
        e10032.setSssDeduction(1080.00);
        e10032.setPhilhealthDeduction(360.00);
        e10032.setPagibigDeduction(100.00);
        e10032.setTaxDeduction(325.40);
        employees.add(e10032);

        // 10033
        Employee e10033 = new ProbationaryEmployee(
                10033, "admin", "Martinez", "Josie",
                "09/27/1990",
                "9 Orchid Street, BF Resort Village, Las Piñas 1747",
                "321-654-987",
                "27-2090996-4", "987654321987", "275-792-678-000", "101205578964",
                "Sales Rank and File", "Aguilar, Lora",
                24000, 1500, 500, 500, 12000, 142.86
        );
        e10033.setSssDeduction(1080.00);
        e10033.setPhilhealthDeduction(360.00);
        e10033.setPagibigDeduction(100.00);
        e10033.setTaxDeduction(325.40);
        employees.add(e10033);

        // 10034
        Employee e10034 = new ProbationaryEmployee(
                10034, "admin", "Santos", "Martha",
                "04/03/1995",
                "15 Mango Avenue, Brgy. Plainview, Mandaluyong 1550",
                "239-842-115",
                "64-7605054-4", "456987123456", "525-420-785-000", "799254187456",
                "Sales Rank and File", "Aguilar, Lora",
                22500, 1500, 500, 500, 11250, 133.93
        );
        e10034.setSssDeduction(1012.50);
        e10034.setPhilhealthDeduction(337.50);
        e10034.setPagibigDeduction(100.00);
        e10034.setTaxDeduction(43.40);
        employees.add(e10034);
    }
}
