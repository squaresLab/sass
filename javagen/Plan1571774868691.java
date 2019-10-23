public class Plan1571774868691 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("A");
StartServer("B");

}

StartServer("A");
for (int i = 0; i < 5 ; i++) {
StartServer("C");
}

for (int i = 0; i < 2 ; i++) {
DecreaseTraffic("A");
}




}
}
