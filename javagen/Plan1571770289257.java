public class Plan1571770289257 extends Plan { 
public static void main(String[] args) { 
StartServer("A");
StartServer("A");
for (int i = 0; i < 5 ; i++) {
StartServer("B");
DecreaseDimmer("A");

}

for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
}



for (int i = 0; i < 4 ; i++) {
StartServer("C");
}



}
}
