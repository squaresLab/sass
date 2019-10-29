public class Plan1571767863835 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 3 ; i++) {
StartServer("C");
}

for (int i = 0; i < 4 ; i++) {
StartServer("A");
}

StartServer("B");


} else {
IncreaseTraffic("C");
}

StartServer("B");

}

}
}
