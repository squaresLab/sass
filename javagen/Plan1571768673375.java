public class Plan1571768673375 extends Plan { 
public static void main(String[] args) { 
StartServer("C");
DecreaseTraffic("A");

for (int i = 0; i < 2 ; i++) {
DecreaseTraffic("A");
}


StartServer("B");
for (int i = 0; i < 4 ; i++) {
StartServer("C");
StartServer("B");

StartServer("A");

}



}
}
