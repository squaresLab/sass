public class Plan1571769043004 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("B");
StartServer("A");
StartServer("C");




}

for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
}


}
}
