public class Plan1571769610764 extends Plan { 
public static void main(String[] args) { 
StartServer("B");
for (int i = 0; i < 3 ; i++) {
StartServer("A");
}


for (int i = 0; i < 3 ; i++) {
StartServer("B");
DecreaseTraffic("A");
for (int i = 0; i < 3 ; i++) {
StartServer("C");
}



}


}
}
