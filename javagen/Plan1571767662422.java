public class Plan1571767662422 extends Plan { 
public static void main(String[] args) { 
StartServer("C");
for (int i = 0; i < 2 ; i++) {
StartServer("A");
}

for (int i = 0; i < 4 ; i++) {
StartServer("B");
}



for (int i = 0; i < 3 ; i++) {
StartServer("A");
StartServer("C");
DecreaseTraffic("A");


}


}
}
