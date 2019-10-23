public class Plan1571769980425 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
DecreaseTraffic("A");
}

for (int i = 0; i < 5 ; i++) {
StartServer("B");
StartServer("C");

}

for (int i = 0; i < 5 ; i++) {
StartServer("A");
}



}
}
