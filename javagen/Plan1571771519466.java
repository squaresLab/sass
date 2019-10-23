public class Plan1571771519466 extends Plan { 
public static void main(String[] args) { 
StartServer("B");
for (int i = 0; i < 5 ; i++) {
DecreaseTraffic("A");
}


for (int i = 0; i < 2 ; i++) {
StartServer("A");
}

for (int i = 0; i < 5 ; i++) {
StartServer("B");
}


DecreaseDimmer("A");

for (int i = 0; i < 4 ; i++) {
StartServer("C");
}



}
}
