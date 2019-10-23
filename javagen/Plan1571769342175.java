public class Plan1571769342175 extends Plan { 
public static void main(String[] args) { 
DecreaseTraffic("A");
DecreaseTraffic("A");
DecreaseTraffic("A");


for (int i = 0; i < 2 ; i++) {
StartServer("C");
}


StartServer("B");

for (int i = 0; i < 4 ; i++) {
StartServer("C");
StartServer("B");

StartServer("A");

}


}
}
