public class Plan1571774343935 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("B");
StartServer("C");
StartServer("A");


}

for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
}

for (int i = 0; i < 2 ; i++) {
StartServer("B");
}



for (int i = 0; i < 4 ; i++) {
StartServer("C");
}


}
}
