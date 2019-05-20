import java.util.*;
import java.io.*;

// jsoup for remote login
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

public class IcLogin {
    
  //////////////////////////////// instance vars /////////////////////////////////////////
  
  private static Elements gradeTables;
  private static List<String> grades = new ArrayList<String>();;
  
  //////////////////////////////// methods ///////////////////////////////////////////////
  
  /**
   * attempts remote login onto IC; helper method for the Dovi.java class.
   * you may have to manually change the gradesView URL for it to work for your user specifically.
   * @param username and password in a string format.
   */
  public static ArrayList<Athlete> athletesInit() {
    
    try {
      //Connection.Response initial = Jsoup
       // .connect("https://icampus.dublinusd.org/campus/portal/dublin.jsp")
        //.method(Connection.Method.GET)
       // .execute();
        final String url = "http://www.diablotiming.com/results/2019-04-27/track_trials.htm";
        
        String html = Jsoup.connect(url).get().html();
    
        List<String> event = new ArrayList<String>(Arrays.asList(html.split("Event")));
        event.remove(0);
        ArrayList<Athlete> athletes = new ArrayList<Athlete>();
        //System.out.println(event.get(event.size()-1));
        for (int eIndex = 0; eIndex < event.size()-2; eIndex++) {
            List<String> heat = new ArrayList<String>(Arrays.asList(event.get(eIndex).split("Heat")));
             //List<String> sections = new ArrayList<String>(Arrays.asList(heat.get(heat.size()-1).split("Section")));
            //System.out.println(heat.get(heat.size()-1));
            //System.out.println(heat.get(0));
            List<String> eventName = new ArrayList<String>(Arrays.asList(heat.get(0).split(" ")));
            
            String eventType = eventName.get(2) + " " + eventName.get(3) +" " +  eventName.get(4) + " " + eventName.get(5)+ " " + eventName.get(6) + " " + eventName.get(7);
            //System.out.println(eventType);
            heat.remove(0);
            heat.remove(0);
            //System.out.println(heat);
            for (int hIndex = 0; hIndex < heat.size();hIndex++){
                
                List<String> athleteInfo = new ArrayList<String>(Arrays.asList(heat.get(hIndex).split("\\r?\\n")));
                athleteInfo.remove(0);
                //ArrayList<String> oneAthlete = new ArrayList<String>(Arrays.asList(athleteInfo.get(1).split(" ")));
                //System.out.println(athleteInfo);
                
                //System.out.println(oneAthlete);
                
                for (int j = 0; j < athleteInfo.size()-1; j++) {
                    //System.out.println(athleteInfo.get(j));
                    ArrayList<String> oneAthlete = new ArrayList<String>(Arrays.asList(athleteInfo.get(j).split(" ")));
                    for (int i = oneAthlete.size()-1; i >=0 ; i--){
                        if (oneAthlete.get(i).equals("")){
                            oneAthlete.remove(i);
                        }
                        else {
                            //System.out.println(oneAthlete.get(i));
                        }
                    }
                    //System.out.println(oneAthlete);
                    //for (){
                        
                        String tempname = (oneAthlete.get(2)+ " " +oneAthlete.get(1)).toLowerCase();
                        String name = tempname.substring(0,tempname.length()-1);
                        String school = oneAthlete.get(4);
                        String grade = oneAthlete.get(3);
                        String seed = oneAthlete.get(0);
                        athletes.add(new Athlete(name, school, grade, seed,eventType));
                    //}
                    
                   // System.out.println(athletes.get(j));
                //System.out.println(athletes.get(j) + "\n");
            
                }   
            }
        }
        return athletes;
        
                
        
     /* final String USER_AGENT = "\"Mozilla/5.0 (Windows NT\" +\n" +
        "          \" 6.1; WOW64) AppleWebKit/535.2 (KHTML, like Gecko) Chrome/15.0.874.120 Safari/535.2\"";
      
      String loginFormUrl = "https://icampus.dublinusd.org/campus/portal/dublin.jsp";
      String loginActionUrl = "https://icampus.dublinusd.org/campus/verify.jsp?nonBrowser=true&username=" + user + "&password=" + pass + "&appName=" + "dublin";

      HashMap<String, String> cookies = new HashMap<>();
      HashMap<String, String> formData = new HashMap<>();
      
      Connection.Response loginForm = Jsoup
        .connect(loginFormUrl)
        .method(Connection.Method.GET)
        .userAgent(USER_AGENT)
        .execute();

      Document loginDoc = loginForm.parse();
      
      for(Map.Entry<String, String> cookie : loginForm.cookies().entrySet()) {
        cookies.put(cookie.getKey(), cookie.getValue());
      }
      
      formData.put("appName", "dublin");
      formData.put("portalUrl", "portal/dublin.jsp");
      formData.put("username", user);
      formData.put("password", pass);

      Connection.Response homePage = Jsoup
        .connect(loginActionUrl)
        .cookies(cookies)
        .data(formData)
        .method(Connection.Method.POST)
        .userAgent(USER_AGENT)
        .execute();
      
      // adding the cookies from the homepage
      for(Map.Entry<String, String> cookie : homePage.cookies().entrySet()) {
        cookies.put(cookie.getKey(), cookie.getValue());
      }
      
      // prints out method if authentication is successful, comment out bc irrelevant lol
      // System.out.println(homePage.parse().html());
      
      Connection.Response gradesView = Jsoup
        .connect("https://icampus.dublinusd.org/campus/portal/portal.xsl?x=portal.PortalOutline&lang=en&X-XSRF-TOKEN=1512dfbb-f9c4-4441-8de1-f4fd900c8c58&personType=student&context=9176-247-230&personID=9176&studentFirstName=James&lastName=Fu&firstName=James&schoolID=7&calendarID=247&structureID=230&calendarName=18-19%20Dublin%20High%20School&mode=grades&x=portal.PortalGrades&X-XSRF-TOKEN=1512dfbb-f9c4-4441-8de1-f4fd900c8c58")
        .cookies(cookies)
        .method(Connection.Method.POST)
        .userAgent(USER_AGENT)
        .execute();

      String grades = gradesView.parse().html();
      
      // prints out full document of the grade view, but only for testing purposes
      // not necessary because we only want to extract the percentage
      // System.out.println(grades);
      
      Document gradeFind = Jsoup.parse(grades);
      
      gradeTables = gradeFind.select("td.inProgressGrade");
      
      // for(Element grade: gradeTables) {
          // System.out.println("\n------------------ :( ------------------\n");
          // System.out.println(grade.text());
          // System.out.println("\n------------------ :( ------------------\n");
      // }
      */}
     catch (IOException e) {
      e.printStackTrace();
      return new ArrayList<Athlete>();
    }
    
        
  }
  public static void checkIn(String searchName, ArrayList <Athlete> athletes) {
      int flag = 0;
        for (Athlete a: athletes){
            
            searchName = searchName.toLowerCase();
            if (searchName.equals(a.getName())){
                a.setCheckedIn(true);
                System.out.println( searchName.toUpperCase() + " is now checked in for" + a.getEvent() + " with a sticker number of " + a.getSeeding());
                flag = 1;
            }
        }
        if (flag == 0 ){
            System.out.println("This athlete is not registered for an event");
        }
        
        
    }
    public static void printPaper(String eventName,ArrayList <Athlete> athletes){
        System.out.println(eventName + ": ");
        for (Athlete a: athletes){
            
            if (a.getCheckedIn() && (a.getEvent().equals(eventName))){
                
                System.out.println(a.getName());
                
           } else {
               
           }
       }
    }
}