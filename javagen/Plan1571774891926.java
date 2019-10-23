public class Plan1571774891926 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("A");
}

DecreaseTraffic("A");
for (int i = 0; i < 4 ; i++) {
StartServer("C");
StartServer("B");

}



}
}
