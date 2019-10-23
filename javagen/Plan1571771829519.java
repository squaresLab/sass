public class Plan1571771829519 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 6 ; i++) {
StartServer("C");
}

for (int i = 0; i < 2 ; i++) {
StartServer("B");
}

StartServer("B");
DecreaseDimmer("A");

DecreaseTraffic("A");
for (int i = 0; i < 4 ; i++) {
StartServer("A");
}




DecreaseTraffic("A");

StartServer("B");


}
}
