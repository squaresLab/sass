public class Plan1571772812387 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("C");
}

StartServer("C");

for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
}

for (int i = 0; i < 5 ; i++) {
StartServer("A");
StartServer("B");

}



StartServer("C");

}
}
