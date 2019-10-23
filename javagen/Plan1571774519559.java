public class Plan1571774519559 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("B");
}

for (int i = 0; i < 3 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("C");
} else {
StartServer("C");
}

StartServer("A");
StartServer("A");


}


}
}
