public class Plan1571768928152 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
StartServer("B");
}

for (int i = 0; i < 3 ; i++) {
if ( StartServer("C") ) {
StartServer("A");
StartServer("B");

} else {
DecreaseDimmer("C");
}

DecreaseTraffic("A");

StartServer("A");

}


}
}
