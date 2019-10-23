public class Plan1571774536610 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
StartServer("C");
DecreaseTraffic("A");

}

StartServer("B");
StartServer("B");
StartServer("A");



StartServer("B");
StartServer("B");
StartServer("A");


StartServer("C");

for (int i = 0; i < 4 ; i++) {
StartServer("B");
}


DecreaseTraffic("A");


StartServer("C");
DecreaseDimmer("A");


StartServer("A");

}
}
