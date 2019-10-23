public class Plan1571771848076 extends Plan { 
public static void main(String[] args) { 
StartServer("A");
StartServer("B");
DecreaseTraffic("A");
DecreaseTraffic("A");


for (int i = 0; i < 4 ; i++) {
StartServer("B");
StartServer("A");
StartServer("C");


}



}
}
