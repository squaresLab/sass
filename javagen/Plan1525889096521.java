public class Plan1525889096521 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("B");
StartServer("A");
DecreaseDimmer("B");


StartServer("C");

}

for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
}

StartServer("C");


}
}
