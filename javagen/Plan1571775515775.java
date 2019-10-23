public class Plan1571775515775 extends Plan { 
public static void main(String[] args) { 
StartServer("B");
for (int i = 0; i < 4 ; i++) {
StartServer("C");
StartServer("A");

}


for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
StartServer("B");
StartServer("C");


}


}
}
