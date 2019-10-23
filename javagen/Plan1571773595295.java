public class Plan1571773595295 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("C");
DecreaseTraffic("A");

for (int i = 0; i < 2 ; i++) {
StartServer("B");
}

StartServer("A");


}

}
}
