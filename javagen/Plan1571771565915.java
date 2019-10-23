public class Plan1571771565915 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("A");
}

if ( DecreaseTraffic("A") ) {
if ( DecreaseDimmer("A") ) {
StartServer("A");
} else {
DecreaseTraffic("A");
}

} else {
StartServer("B");
}

for (int i = 0; i < 4 ; i++) {
StartServer("B");
StartServer("C");

}



}
}
