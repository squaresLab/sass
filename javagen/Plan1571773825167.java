public class Plan1571773825167 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("C");
StartServer("B");

}

StartServer("A");
for (int i = 0; i < 4 ; i++) {
DecreaseTraffic("A");
}



}
}
