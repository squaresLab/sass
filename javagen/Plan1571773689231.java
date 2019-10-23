public class Plan1571773689231 extends Plan { 
public static void main(String[] args) { 
DecreaseDimmer("B");
StartServer("A");
for (int i = 0; i < 3 ; i++) {
StartServer("B");
StartServer("C");
DecreaseTraffic("A");


StartServer("B");
StartServer("A");


}

for (int i = 0; i < 2 ; i++) {
StartServer("C");
}




}
}
