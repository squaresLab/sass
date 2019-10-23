public class Plan1571774334129 extends Plan { 
public static void main(String[] args) { 
StartServer("A");
DecreaseTraffic("A");
for (int i = 0; i < 4 ; i++) {
StartServer("C");
StartServer("A");

}

for (int i = 0; i < 6 ; i++) {
StartServer("B");
}


DecreaseDimmer("A");



}
}
