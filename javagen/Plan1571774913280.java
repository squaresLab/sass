public class Plan1571774913280 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
StartServer("B");
StartServer("A");


for (int i = 0; i < 2 ; i++) {
StartServer("C");
}


}

StartServer("B");
StartServer("A");


}
}
